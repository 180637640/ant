package cn.hs.service.impl;

import cn.hs.bean.JobBean;
import cn.hs.service.JobService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取Job信息缓存
 * @author swt
 */
@Service
public class JobServiceImpl extends BaseServiceImpl implements JobService {
	private static final String TYPE_NAME = JobServiceImpl.class.getTypeName();

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean start(String name, long nextUpdateTime) {
		JobBean o = get(name);
		if(null == o) {
			o = new JobBean();
		}
		o.setType(1);
		o.setName(name);
		o.setStatus(2);
		o.setTotalRow(0);
		// 状态(1:待执行 2:执行中 3:异常 4：执行结束)
		o.setStatus(2);
		o.setNextUpdateTime(nextUpdateTime);
		put(o);
		return true;
	}

	@Override
	public boolean end(String name) {
		JobBean o = get(name);
		if(null == o) {
			return false;
		}
		// 状态(1:待执行 2:执行中 3:异常 4：执行结束)
		o.setStatus(4);
		put(o);
		return true;
	}

	@Override
	public boolean exception(String name, String latestException) {
		JobBean o = get(name);
		if(null == o) {
			return false;
		}
		o.setStatus(3);
		o.setLatestException(latestException);
		put(o);
		return true;
	}

	@Override
	public boolean canExecute(String name) {
		JobBean o = get(name);
		if(null == o) {
			return true;
		}
		long timeout = System.currentTimeMillis() - o.getNextUpdateTime();
		if(timeout < 0) {
			return false;
		}
		// 状态(1:待执行 2:执行中 3:异常 4：执行结束)
		// 状态不在进行中或超过6小时
		int duration = 6 * 60 * 60 * 1000;
		if(o.getStatus() != 2 || timeout > duration) {
			return true;
		}

		return false;
	}

	@Override
	public void addRow(String name, Integer row) {
		JobBean o = get(name);
		if(null == o) {
			return;
		}
		o.setTotalRow(o.getTotalRow() + row);
		put(o);
	}

	@Override
	public List<JobBean> list() {
		List<JobBean> list = new ArrayList<>();
		List<Object> objList = redisTemplate.opsForHash().values(getPrefix());
		for (Object o : objList) {
			if(null == o) {
				continue;
			}
			list.add((JobBean) o);
		}

		return list;
	}

	private JobBean get(String name) {
		if(StringUtils.isBlank(name)) {
			return null;
		}
		Object o = redisTemplate.opsForHash().get(getPrefix(), name);
		if(null == o) {
			return null;
		}
		return (JobBean) o;
	}

	private void put(JobBean o) {
		redisTemplate.opsForHash().put(getPrefix(), o.getName(), o);
	}

	/**
	 * 获取缓存标识前缀
	 * @return	前缀
	 */
	protected String getPrefix() {
		return TYPE_NAME;
	}
}

