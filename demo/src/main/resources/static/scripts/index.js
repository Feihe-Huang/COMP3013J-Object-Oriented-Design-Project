layui.use(['carousel', 'element'], function () {
    let carousel = layui.carousel;
    let element = layui.element;

    //建造实例
    carousel.render({
        elem: '#index'
        , width: '900' //设置容器宽度
        , height: '550px' //设置容器高度
        // , full:'true' //是否全屏轮播,默认false
        // , arrow: 'always' //始终显示箭头和点击按钮
        // ,anim: 'updown' //切换动画方式，可从各个方向滚动
    });
});