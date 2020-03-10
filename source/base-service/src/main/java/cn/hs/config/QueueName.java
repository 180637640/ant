package cn.hs.config;


/**
 * 队列名称
 * @author swt
 */
public class QueueName {

	/** 访问日志队列 */
	public static final String QUEUE_SYS_VISIT_LOG = "sys:log:visit";

	/** 操作日志队列 */
	public static final String QUEUE_SYS_LOG = "sys:log";

	/** GPS队列 */
	public static final String QUEUE_INTERFACE_GPS = "queue:interface:gps";

	/** GPS队列（万马） */
	public static final String QUEUE_INTERFACE_GPS_WM = "queue:interface:gps:wm";

	/** GPS设备健康状况交换机（万马） */
	public static final String EXCHANGE_INTERFACE_GPS_WM_STATUS = "exchange:interface:gps:wm:status";

	/** GPS队列（湘湖） */
	public static final String QUEUE_INTERFACE_GPS_XH = "queue:interface:gps:xh";

	/** AIS队列 */
	public static final String QUEUE_INTERFACE_AIS = "queue:interface:ais";

	/** AIS队列(基站定位) */
	public static final String QUEUE_INTERFACE_AIS_STATION = "queue:interface:ais:station";

	/** AIS队列(基站定位-TCP接收) */
	public static final String QUEUE_INTERFACE_AIS_STATION_TCP = "queue:interface:ais:station:tcp";

	/** AIS队列(四川) */
	public static final String QUEUE_INTERFACE_AIS_SC = "queue:interface:ais:sc";

	/** AIS队列(北京云海佳信科技有限公司) */
	public static final String QUEUE_INTERFACE_AIS_YH = "queue:interface:ais:yh";

	/** 船舶定位交换机 */
	public static final String EXCHANGE_INTERFACE_POS = "exchange:interface:pos";

	/** 船舶状态交换机 */
	public static final String EXCHANGE_INTERFACE_POS_STATUS = "exchange:interface:pos:status";

	/** 船舶定位消费 */
	public static final String QUEUE_INTERFACE_POS = "queue:interface:pos";

	/** 船舶状态消费 */
	public static final String QUEUE_INTERFACE_POS_STATUS = "queue:interface:pos:status";

	/** RFID队列 */
	public static final String QUEUE_INTERFACE_RFID = "queue:interface:rfid";

	/** RFID队列(基站) */
	public static final String QUEUE_INTERFACE_RFID_STATION = "queue:interface:rfid:station";

	/** 核查项目队列前缀*/
	public static final String QUEUE_CHECK_PRE = "queue:check:";

	/** 下载队列 */
	public static final String QUEUE_DOWNLOAD_URL = "queue:download:url";

	/** 船舶核查结果队列 */
	public static final String QUEUE_SHIP_CHECK_RESULT= "queue:ship:check:result";

	/**船舶信息队列**/
	public static final String	QUEUE_SHIP_EXTEND_V_CB_CBJBXX = "queue:collaboration:ship:extend:v_cb_cbjbxx";
	/**证书信息队列**/
	public static final String	QUEUE_CERTIFICATE_ALL_V_ZHCX_CBZSXX = "queue:collaboration:certificate:all:v_zhcx_cbzsxx";
	/**证书信息队列外省**/
	public static final String	QUEUE_CERTIFICATE_ALL_WS_V_ZHCX_CBZSXX_WS = "queue:collaboration:certificate:all:ws:v_zhcx_cbzsxx_ws";
	/**船舶违章信息队列**/
	public static final String	QUEUE_VIOLATION_V_XZCF_CBWZ_HST = "queue:collaboration:violation:v_xzcf_cbwz_hst";
	/**船员基本信息**/
	public static final String	QUEUE_CREW_V_CY_CYJBXX = "queue:collaboration:crew:v_cy_cyjbxx";
	/**港口企业信息队列**/
	public static final String	QUEUE_PORTENTERPRISE_V_XZXK_GKQYJBXX = "queue:collaboration:portenterprise:v_xzxk_gkqyjbxx";
	/**业务基本信息队列**/
	public static final String	QUEUE_BASICINFORMATION_V_XZXK_YWJBXX = "queue:collaboration:basicinformation:v_xzxk_ywjbxx";
	/**船舶试航证书队列**/
	public static final String	QUEUE_AIRWORTHINESS_V_ZS_CB_CBCSH= "queue:collaboration:airworthiness:v_zs_cb_cbcsh";
	/**船舶国籍证书信息队列**/
	public static final String	QUEUE_NATIONALITYCERTIFICATE_V_ZS_CB_CBGJ= "queue:collaboration:nationalitycertificate:v_zs_cb_cbgj";

	/**船检主机信息队列**/
	public static final String	QUEUE_SHIPDETECTION_V_CJ_ZJQKB= "queue:collaboration:shipdetection" +
			":v_cj_zjqkb";

	/**水路运输企业水路运输证申请信息队列**/
	public static final String	QUEUE_WATERWAYTRANSPORT_V_CJ_ZJQKB= "queue:collaboration:waterwaytransport" +
			":v_xzxk_syqyjbxx";

	/**立案信息队列**/
	public static final String QUEUE_FILEINFORMATION_V_XZCF_LAXX="queue:collaboration:fileinformation" + ":v_xzcf_laxx";

	/**案件基本信息**/
	public static final String	QUEUE_COLLABORATIONBASICCASE_V_XZCF_AJJBXX= "queue:collaboration:collaborationbasiccase" +
			":v_xzcf_ajjbxx";

    /**调查结论及处罚意见**/
    public static final String	QUEUE_PENALTYOPINIONS_V_XZCF_CFYJ= "queue:collaboration:penaltyopinions" +
            ":v_xzcf_cfyj";

	/**船检_船舶基本情况**/
	public static final String	QUEUE_SHIPINSPECTION_V_CJ_CBJBQK= "queue:collaboration:shipinspection" +
			":v_cj_cbjbqk";

    /**船检_下次检验情况**/
    public static final String	QUEUE_SHIPNEXTTIME_V_CJ_XCJYQK= "queue:collaboration:shipnexttime" +
            ":v_cj_xcjyqk";

	/**水路运输经营许可证(新)**/
	public static final String	QUEUE_WATERWAYLICENSE_V_ZS_SLYSJYXKZ= "queue:collaboration:waterwaylicense" +
			":v_zs_slysjyxkz";

    /**船检签发证书情况**/
    public static final String	QUEUE_CERTIFICATE_V_CJ_QFZSQK= "queue:collaboration:certificate" +
            ":v_cj_qfzsqk";

	/**内河船员适住证书**/
	public static final String	QUEUE_SEAMENFIT_V_ZS_NH_CYSRZ= "queue:collaboration:seamenfit" +
			":v_zs_nh_cysrz";


	/** 船舶信息交换机 */
	public static final String EXCHANGE_INTERFACE_DATA_XT_VIEW_V_CB_CBJBXX = "exchange:interface:data:xt:view:v_cb_cbjbxx";
	/** 证书综合信息(本省) */
	public static final String EXCHANGE_INTERFACE_DATA_XT_VIEW_V_ZHCX_CBZSXX = "exchange:interface:data:xt:view:v_zhcx_cbzsxx";
	/** 证书综合信息(外省) */
	public static final String EXCHANGE_INTERFACE_DATA_XT_VIEW_V_ZHCX_CBZSXX_WS = "exchange:interface:data:xt:view:v_zhcx_cbzsxx_ws";
	/** 船舶违章信息*/
	public static final String EXCHANGE_INTERFACE_DATA_XT_VIEW_V_XZCF_CBWZ_HST = "exchange:interface:data:xt:view:v_xzcf_cbwz_hst";
	/** 船员基本信息*/
	public static final String EXCHANGE_INTERFACE_DATA_XT_VIEW_V_CY_CYJBXX = "exchange:interface:data:xt:view:v_cy_cyjbxx";

	/**港口企业信息*/
	public static final String EXCHANGE_INTERFACE_DATA_XT_VIEW_V_XZXK_GKQYJBXX = "exchange:interface:data:xt:view:v_xzxk_gkqyjbxx";

	/**业务基本信息*/
	public static final String EXCHANGE_INTERFACE_DATA_XT_VIEW_V_XZXK_YWJBXX = "exchange:interface:data:xt:view:v_xzxk_ywjbxx";

	/**船舶试航证书基本信息*/
	public static final String EXCHANGE_INTERFACE_DATA_XT_VIEW_V_ZS_CB_CBCSH = "exchange:interface:data:xt:view:v_zs_cb_cbcsh";

	/**船舶国籍证书信息*/
	public static final String EXCHANGE_INTERFACE_DATA_XT_VIEW_V_ZS_CB_CBGJ = "exchange:interface:data:xt:view:v_zs_cb_cbgj";

	/**船检主机信息*/
	public static final String EXCHANGE_INTERFACE_DATA_XT_VIEW_V_CJ_ZJQKB = "exchange:interface:data:xt:view:v_cj_zjqkb";

	/**水路运输企业水路运输证申请信息*/
	public static final String EXCHANGE_WATERWAYTRANSPORT_V_XZXK_SYQYJBXX = "exchange:interface:data:xt:view:v_xzxk_syqyjbxx";

	/**案件基本信息*/
	public static final String EXCHANGE_COLLABORATIONBASICCASE_V_XZCF_AJJBXX = "exchange:interface:data:xt:view:v_xzcf_ajjbxx";

	/**立案信息交换机*/
	public static final String EXCHANGE_FILEINFORMATION_V_XZCF_LAXX = "exchange:interface:data:xt:view:v_xzcf_laxx";

    /**调查结论及处罚意见*/
    public static final String EXCHANGE_PENALTYOPINIONS_V_XZCF_CFYJ = "exchange:interface:data:xt:view:v_xzcf_cfyj";

	/**船检_船舶基本情况*/
	public static final String EXCHANGE_SHIPINSPECTION_V_CJ_CBJBQK = "exchange:interface:data:xt:view:v_cj_cbjbqk";

	/**船检_下次检验情况*/
    public static final String EXCHANGE_SHIPNEXTTIME_V_CJ_XCJYQK = "exchange:interface:data:xt:view:v_cj_xcjyqk";

	/**水路运输经营许可证(新)*/
	public static final String EXCHANGE_WATERWAYLICENSE_V_ZS_SLYSJYXKZ = "exchange:interface:data:xt:view:v_zs_slysjyxkz";

    /**船检签发证书情况*/
    public static final String EXCHANGE_CERTIFICATE_V_CJ_QFZSQK = "exchange:interface:data:xt:view:v_cj_qfzsqk";

	/**内河船员适住信息*/
	public static final String EXCHANGE_SEAMENFIT_V_ZS_NH_CYSRZ = "exchange:interface:data:xt:view:v_zs_nh_cysrz";




}
