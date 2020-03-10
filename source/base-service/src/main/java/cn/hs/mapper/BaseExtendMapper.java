package cn.hs.mapper;



import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 扩展服务
 * @author swt
 */
public interface BaseExtendMapper<T> extends BaseMapper<T> {

	/**
	 * 更新指定属性
	 * @param params			查询参数
	 * @return					当前页记录数
	 */
	int updateByPrimaryKeySelective(Map<String, Object> params);

	/**
	 * 分页查询
	 * @param params			查询参数
	 * @return					当前页记录数
	 */
	List<T> query(Map<String, Object> params);

	/**
	 * 唯一性验证
	 * @param params			查询参数
	 * @return					总记录数
	 */
	int exists(Map<String, Object> params);


}
