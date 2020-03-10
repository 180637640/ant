package cn.hs.service.impl;

import cn.hs.bean.LoginBean;
import cn.hs.bean.base.DataTable;
import cn.hs.bean.base.Page;
import cn.hs.bean.type.OnlineType;
import cn.hs.service.LoginCacheService;
import cn.hs.service.LoginInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 登录缓存管理
 * 原理：
 * 根据sessionID查找ID
 * 根据ID查找Bean
 * 根据ID和类型查找最新sessionID
 * @author swt
 */
@Service
public class LoginInfoServiceImpl extends BaseServiceImpl implements LoginInfoService {
	public static final String TYPE_NAME = LoginInfoServiceImpl.class.getTypeName();
	public static final String KEY_ID    = "LOGIN:ID:";
	public static final String KEY_OBJ   = "LOGIN:OBJ:";
	public static final String KEY_SET   = "LOGIN:SET:";
	/** 最大保存时间（单位小时） */
	private static final long MAX_ACTIVE_HOURS = 30 * 24;

	@Resource
	protected LoginCacheService loginCacheService;

	@Override
	public LoginBean get(String key) {
		return get(key, false);
	}

	@Override
	public LoginBean put(String key, LoginBean value) {
		loginCacheService.setCache(TYPE_NAME, key, value.getId(), MAX_ACTIVE_HOURS, TimeUnit.HOURS);
		loginCacheService.setCache(TYPE_NAME, KEY_ID + value.getId(), key, MAX_ACTIVE_HOURS, TimeUnit.HOURS);
		loginCacheService.setCache(TYPE_NAME, KEY_OBJ + value.getId(), value, MAX_ACTIVE_HOURS, TimeUnit.HOURS);
		loginCacheService.opsForSetAdd(TYPE_NAME, KEY_SET, value.getId());
		return value;
	}

	@Override
	public LoginBean remove(String key) {
		LoginBean obj = get(key, false);

		// 清理缓存
		loginCacheService.deleteCache(TYPE_NAME, key);

		if(null != obj && obj.getOnlineType() == OnlineType.ONLINE){
			// 如果未离线，则清除
			loginCacheService.deleteCache(TYPE_NAME, obj.getId());
			loginCacheService.deleteCache(TYPE_NAME, obj.getId());
			loginCacheService.opsForSetRemove(TYPE_NAME, KEY_SET, obj.getId());
		}

		return obj;
	}

	@Override
	public void refreshTime(String key) {
		get(key, true);
	}

	@Override
	public DataTable<LoginBean> query(int pageNum, int pageSize, Integer loginType, String keyword) {
		Set<Object> set = loginCacheService.opsForSetMembers(TYPE_NAME, KEY_SET);
		List<String> keys = new ArrayList<>();
		for (Object o : set) {
			if(o == null) {
				continue;
			}
			keys.add(KEY_OBJ + o);
		}
		DataTable<LoginBean> dataTable = new DataTable<>();
		dataTable.setPage(new Page());
		dataTable.setDataList(new ArrayList<>());
		dataTable.getPage().setPageNum(pageNum);
		dataTable.getPage().setPageSize(pageSize);

		// 获取所有在线用户
		List<Object> list = loginCacheService.getCache(TYPE_NAME, keys);
		if(null == list || list.isEmpty()) {
			return dataTable;
		}
		// 类型转换
		List<LoginBean> dataList = new ArrayList<>();
		for (Object o : list) {
			if(null == o || !(o instanceof LoginBean)) {
				continue;
			}
			dataList.add((LoginBean) o);
		}
		// 数据筛选+排序
		dataList = dataList.stream()
				.filter((o)->{
					if(null != loginType && loginType != o.getLoginType()) {
						return false;
					}
					if(StringUtils.isNotBlank(keyword) && !o.getLoginName().contains(keyword)) {
						return false;
					}
					return true;
				})
				.sorted((o1, o2) -> {
					/*if(o1.getLoginType() > o2.getLoginType()) {
						return 1;
					} else if(o1.getLoginType() < o2.getLoginType()) {
						return -1;
					} else {
						return o1.getLoginName().compareTo(o2.getLoginName());
					}*/
					// 按最后刷新时间排序
					if(o1.getUpdateTimeMillis() > o2.getUpdateTimeMillis()) {
						return -1;
					} else if(o1.getUpdateTimeMillis() < o2.getUpdateTimeMillis()) {
						return 1;
					} else {
						return 0;
					}
				})
				.collect(Collectors.toList());

		if(pageNum >= 1) {
			pageNum = pageNum - 1;
		} else {
			pageNum = 0;
		}
		int fromIndex = pageNum * pageSize;
		int toIndex = fromIndex + pageSize;
		if(dataList.size() < fromIndex) {
			return dataTable;
		} else if(dataList.size() < toIndex) {
			toIndex = dataList.size();
		}
		dataTable.getPage().setTotal(dataList.size());
		dataList = dataList.subList(fromIndex, toIndex);
		dataTable.setDataList(dataList);

		return dataTable;
	}

	/**
	 * 获取登录信息
	 * @param key			accessToken
	 * @param refresh		是否刷新时间
	 * @return
	 */
	protected LoginBean get(String key, boolean refresh) {
		// 根据Key查找ID
		Object obj = loginCacheService.getCache(TYPE_NAME, key);
		if(null == obj || !(obj instanceof String)) {
			return null;
		}

		// 根据ID查找最新accessToken
		String id = (String) obj;
		obj = loginCacheService.getCache(TYPE_NAME, KEY_ID + id);
		if(null == obj || !(obj instanceof String)) {
			return null;
		}

		// 根据ID查找最新Bean
		String accessToken = (String) obj;
		obj = loginCacheService.getCache(TYPE_NAME, KEY_OBJ + id);
		if(null == obj || !(obj instanceof LoginBean)) {
			return null;
		}

		LoginBean loginBean = (LoginBean) obj;
		if(!StringUtils.equals(key, accessToken)){
			// 如果不是最后登录，则视为离线
			loginBean.setOnlineType(OnlineType.OFFLINE);
			return loginBean;
		}
		if(refresh) {
			// 刷新更新时间
			loginBean.setUpdateTimeMillis(System.currentTimeMillis());
			loginCacheService.setCache(TYPE_NAME, KEY_OBJ + loginBean.getId(), loginBean, MAX_ACTIVE_HOURS, TimeUnit.HOURS);
		}

		return loginBean;
	}


}

