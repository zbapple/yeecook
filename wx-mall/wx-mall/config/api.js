
const root = '/shop/api/';
// const NewApiRootUrl = 'http://192.168.3.53:8082' + root;
const NewApiRootUrl = 'https://www.yeecook.com' + root; 
// const NewApiRootUrl = 'http://192.168.3.182:8080' + root;
// const NewApiRootUrl ='http://192.168.3.24:8081'+ root;
module.exports = { 
  IndexUrlHotGoods: NewApiRootUrl + 'index/hotGoods', //首页数据接口
  IndexUrlTopic: NewApiRootUrl + 'index/topic', //首页数据接口
  IndexUrlBrand: NewApiRootUrl + 'index/brand', //首页数据接口IndexUrlChannel
  IndexUrlCategory: NewApiRootUrl + 'index/category', //首页数据接口IndexUrlChannel
  IndexUrlBanner: NewApiRootUrl + 'index/banner', //首页数据接口IndexUrlChannel
  IndexUrlChannel: NewApiRootUrl + 'index/channel', //首页数据接口IndexUrlChannel
    CatalogList: NewApiRootUrl + 'catalog/index',  //分类目录全部分类数据接口
    CatalogCurrent: NewApiRootUrl + 'catalog/current',  //分类目录当前分类数据接口
    
    AuthLoginByWeixin: NewApiRootUrl + 'auth/login_by_weixin', //微信登录

    GoodsCount: NewApiRootUrl + 'goods/count',  //统计商品总数
    GoodsList: NewApiRootUrl + 'goods/list',  //获得商品列表
    GoodsCategory: NewApiRootUrl + 'goods/category',  //获得分类数据 
    GoodsDetail: NewApiRootUrl + 'goods/detail',  //获得商品的详情
    GoodsNew: NewApiRootUrl + 'goods/new',  //新品
    GoodsHot: NewApiRootUrl + 'goods/hot',  //热门
    GoodsRelated: NewApiRootUrl + 'goods/related',  //商品详情页的关联商品（大家都在看）

    BrandList: NewApiRootUrl + 'brand/list',  //品牌列表
    BrandDetail: NewApiRootUrl + 'brand/detail',  //品牌详情

    CartList: NewApiRootUrl + 'cart/index', //获取购物车的数据
    CartAdd: NewApiRootUrl + 'cart/add', // 添加商品到购物车
    BuyAdd: NewApiRootUrl + 'buy/add', // 直接购买    
    CartUpdate: NewApiRootUrl + 'cart/update', // 更新购物车的商品
    CartDelete: NewApiRootUrl + 'cart/delete', // 删除购物车的商品
    CartChecked: NewApiRootUrl + 'cart/checked', // 选择或取消选择商品
    CartGoodsCount: NewApiRootUrl + 'cart/goodscount', // 获取购物车商品件数
    CartCheckout: NewApiRootUrl + 'cart/checkout', // 下单前信息确认

    BuyCheckout: NewApiRootUrl + 'buy/checkout', // 下单前信息确认

    OrderSubmit: NewApiRootUrl + 'order/submit', // 提交订单
    PayPrepayId: NewApiRootUrl + 'pay/prepay', //获取微信统一下单prepay_id

    CollectList: NewApiRootUrl + 'collect/list',  //收藏列表
    CollectAddOrDelete: NewApiRootUrl + 'collect/addordelete',  //添加或取消收藏

    CommentList: NewApiRootUrl + 'comment/list',  //评论列表
    CommentCount: NewApiRootUrl + 'comment/count',  //评论总数
    CommentPost: NewApiRootUrl + 'comment/post',   //发表评论

    TopicList: NewApiRootUrl + 'topic/list',  //专题列表
    TopicDetail: NewApiRootUrl + 'topic/detail',  //专题详情
    TopicRelated: NewApiRootUrl + 'topic/related',  //相关专题

    SearchIndex: NewApiRootUrl + 'search/index',  //搜索页面数据
    SearchResult: NewApiRootUrl + 'search/result',  //搜索数据
    SearchHelper: NewApiRootUrl + 'search/helper',  //搜索帮助
    SearchClearHistory: NewApiRootUrl + 'search/clearhistory',  //搜索帮助

    AddressList: NewApiRootUrl + 'address/list',  //收货地址列表
    AddressDetail: NewApiRootUrl + 'address/detail',  //收货地址详情
    AddressSave: NewApiRootUrl + 'address/save',  //保存收货地址
    AddressDelete: NewApiRootUrl + 'address/delete',  //保存收货地址

    RegionList: NewApiRootUrl + 'region/list',  //获取区域列表

    OrderList: NewApiRootUrl + 'order/list',  //订单列表
    OrderDetail: NewApiRootUrl + 'order/detail',  //订单详情
    OrderCancel: NewApiRootUrl + 'order/cancelOrder',  //取消订单
    OrderConfirm: NewApiRootUrl + 'order/confirmOrder',  //确认收货

    FootprintList: NewApiRootUrl + 'footprint/list',  //足迹列表
    FootprintDelete: NewApiRootUrl + 'footprint/delete',  //删除足迹
    
    FeedbackAdd: NewApiRootUrl + 'feedback/save', //添加反馈
    SmsCode: NewApiRootUrl + 'user/smscode', //发送短信
    BindMobile: NewApiRootUrl + 'user/bindMobile', //绑定手机
    Login: NewApiRootUrl + 'auth/login', //账号登录
    Register: NewApiRootUrl + 'auth/register', //注册
    CouponList: NewApiRootUrl + 'coupon/list', // 优惠券列表
    GoodsCouponList: NewApiRootUrl + 'coupon/listByGoods', // 商品优惠券列表   
    OrderQuery: NewApiRootUrl + 'pay/query',
    OrderSuccess: NewApiRootUrl + 'order/updateSuccess',
    CardQuery: NewApiRootUrl + 'card/getCard', //得到卡类商品信息
    CardActivation: NewApiRootUrl + 'card/activationCard',//激活
    HelpTypeList: NewApiRootUrl + 'helpissue/typeList', //查看帮助类型列表
    HelpIssueList: NewApiRootUrl + 'helpissue/issueList', //查看问题列表
    PrintList: NewApiRootUrl + 'xetyqm/list', //邀请码打印信息查看  
    Print: NewApiRootUrl + 'xetyqm/print', //打印邀请码
  Cookbook: NewApiRootUrl +'newmenu/index',//餐单首页 
  Cookbookcontent: NewApiRootUrl +'newmenulist/menuinfo',//餐单二级页面
  Cookbookcontent: NewApiRootUrl +'newmenulist/menuinfo',//餐单二级页面
  Cookbookdetail: NewApiRootUrl +'products/poductslist',//具体菜市

  Userbodyinfo: NewApiRootUrl +'userbodyinfo/info',//我的身体数据
  Userbodyupdate: NewApiRootUrl +'userbodyinfo/update',//修改我的身体数据
  Userbodyinfoadd: NewApiRootUrl + 'userbodyinfo/add',//添加身体数据

  Reportlist: NewApiRootUrl +'userhealthreport/userdateinfo',//身体健康记录报表
  Userhealthreportdetail: NewApiRootUrl +'userhealthreport/userbodyinfo',//身体健康具体信息
  Uuserhealthreportmaxuser: NewApiRootUrl +'userhealthreport/maxuser',//身体数据的平均值
  Userhealthreport: NewApiRootUrl + 'userhealthreport/add',//添加用户身体健康报
  Userdetectioncycleinfo: NewApiRootUrl +'userdetectioncycle/info',//首页检测周期
  MenuDetailsmenuinfo: NewApiRootUrl +'MenuDetails/menuinfo',//首页的每日个人食谱
  Usernutritioninfo: NewApiRootUrl +'usernutrition/info',

  Cloudclassroombaner: NewApiRootUrl +'cloudclassroom/baner',//云课堂轮播图
  Cloudclassroomlist: NewApiRootUrl +'cloudclassroom/list',//云课堂播放列表
  Cloudclassroomdetail: NewApiRootUrl + 'cloudclassroom/baner',//云课堂详情
  Cloudclassroomgoodinfo: NewApiRootUrl + 'cloudclassroomgood/info',//获取视频详情的商品
  Usercommentsinfo: NewApiRootUrl +'usercomments/info' ,//获取视频评论

  MenuDetailstodayinfo: NewApiRootUrl+'MenuDetails/todayinfo',//每日套餐的菜谱
  Dishesdishesinfo: NewApiRootUrl +'dishes/dishesinfo',//菜谱详情
  DishesSteps: NewApiRootUrl +'DishesSteps/dishesstepinfo',//菜谱步骤
  Dishesnutrieninfo: NewApiRootUrl +'nutrientelements/nutrieninfo',//营养元素
  Foodingredientsinfo: NewApiRootUrl + 'foodingredients/info',//食材
  Nutrientelementsproportion: NewApiRootUrl+'nutrientelements/proportion'//营养比例
};
