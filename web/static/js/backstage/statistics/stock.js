var myChart = echarts.init(document.getElementById('main'));
var ecConfig = require('echarts/config');
/**原来 TIMELINE_CHANGED 的使用方法有2个参数！2个参数！2个参数！（重要的事情说三遍）*/
myChart.on(ecConfig.EVENT.TIMELINE_CHANGED, function(paramA,paramB){
    /**
     * 通过Console.log(paramA);Console.log(paramB);
     * 可以查看到通过两个参数分别可以获取到什么属性
     * paramA 可以获取当前timeline节点的索引
     * paramB 则可以获取到options数组
     */
        //Console.log(paramA);
        //Console.log(paramB);
    var option=myChart.getOption();
    /**
     * 这段代码是关键
     * 首先通过调试查看到xAxis在Option中的引用位置（看到xAxis是数组）即option.xAxis[0].data
     * paramA.currentIndex 获取到当前timeline节点的索引
     * paramB.component.timeline.options[paramA.currentIndex] 获取到当前引用的options数组
     * 相同的 paramB.component.timeline.timelineOption 则能获取到timeline属性了
     * 别问我为什么知道的，调试！调试！调试！
     */
    option.xAxis[0].data=paramB.component.timeline.options[paramA.currentIndex].xAxis.data;
    /**
     * myChart.setOption(option,true); 是告诉图形不合并数据，一定要设置为true，不然多出来的数据会一直存在！会一直存在！会一直存在！
     */
    myChart.setOption(option,true);
    /**
     * myChart.resize(); 在这里一定要使用这句话，不然会丢失时间轴！会丢失时间轴！会丢失时间轴！
     */
    myChart.resize();
});
var option={
    timeline:{
        data:['2013-01-01','2014-01-01','2015-01-01'],
        label : {
            formatter : function(s) {
                return s.slice(0, 4);
            }
        },
        autoPlay : true,
        playInterval : 1000
    },
    options:[
        {
            /**options 1 */
            title : {text: '2013'},
            tooltip : {trigger: 'axis'},
            xAxis:{data:['A1','B1','C1']},
            yAxis:{type : 'value'},
            series:[{name: '2013',type: 'bar',data:[11,12,14]}]
        },
        {
            /**options 2 */
            title:{text:'2014'},
            xAxis:{data:['A2','B2','C2','D2']},//length=4
            series :[{name: '2014',data:[14,5,56,25]}]
        },
        {
            /**options 3 */
            title:{text:'2015'},
            xAxis:{data:['A3','B3','C3']},
            series :[{name: '2015',data:[12,45,26]}]
        }
    ]
};
myChart.setOption(option);