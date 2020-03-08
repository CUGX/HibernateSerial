package view;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;


public class Chart extends JFrame {
	public Chart() {
	}

	public static XYSeries xyTemseries = new XYSeries("tem");
	public static XYSeries xyHumseries1 = new XYSeries("hum");

    public static int hundroud = 0;
    public static int hundroud1 = 0;
    public static JFreeChart jfreechart = null;
    
    public JPanel getTemJFreeChart(){

        jfreechart = ChartFactory.createXYLineChart(
                null, "time/s","Tem/Hum", (XYDataset) createDataset1(),
                PlotOrientation.VERTICAL, true, true, false);

        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
//        mChartTheme.setDomainGridlinePaint(Color.RED);
        ChartFactory.setChartTheme(mChartTheme);

        jfreechart.setBorderPaint(new Color(0,204,205));
        jfreechart.setBorderVisible(true);

        XYPlot xyplot = (XYPlot) jfreechart.getPlot();

        // Y轴
        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setLowerBound(0);
        numberaxis.setUpperBound(70);
        numberaxis.setTickUnit(new NumberTickUnit(100d));
        // 只显示整数值
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // numberaxis.setAutoRangeIncludesZero(true);
        numberaxis.setLowerMargin(0); // 数据轴下（左）边距 ­
        numberaxis.setMinorTickMarksVisible(false);// 标记线是否显示
        numberaxis.setTickMarkInsideLength(0);// 外刻度线向内长度
        numberaxis.setTickMarkOutsideLength(0);

        // X轴的设计
        NumberAxis x = (NumberAxis) xyplot.getDomainAxis();
        x.setAutoRange(true);// 自动设置数据轴数据范围
        // 自己设置横坐标的值
        x.setAutoTickUnitSelection(false);
        x.setTickUnit(new NumberTickUnit(60d));
        // 设置最大的显示值和最小的显示值
        x.setLowerBound(0);
        x.setUpperBound(60);
        // 数据轴的数据标签：只显示整数标签
        x.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        x.setAxisLineVisible(true);// X轴竖线是否显示
        x.setTickMarksVisible(true);// 标记线是否显示

        RectangleInsets offset = new RectangleInsets(0, 0, 0, 0);
        xyplot.setAxisOffset(offset);// 坐标轴到数据区的间距
        xyplot.setBackgroundAlpha(0.0f);// 去掉柱状图的背景色
        xyplot.setOutlinePaint(null);// 去掉边框

//         ChartPanel chartPanel = new ChartPanel(jfreechart);
//         chartPanel.restoreAutoDomainBounds();//重置X轴

        ChartPanel chartPanel = new ChartPanel(jfreechart, true);

        return chartPanel;
    }

    /**
     * 该方法是数据的设计
     * 
     * @return
     */
    public static XYDataset createDataset1() {
        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(xyTemseries);
        xyseriescollection.addSeries(xyHumseries1);
        return xyseriescollection;
    }

    /**
     * 画动态折线图，每秒显示一个温湿度的值，并于前一个点连成折线图
     * 每60s删除是所有点重画一次折线图的每个点。
     */
    public static void dynamicRun() {
        int i = 0;
        while (true) {
        	double factor = serialView.tem;
            hundroud = (int) factor;
            double factor1 = serialView.hum;
            hundroud1 = (int) factor1;
            
            jfreechart.setTitle("Trend map:  "+"Tem="+hundroud+"degrees; "+"Hum="+hundroud1+"%");
            jfreechart.getTitle().setFont(new Font("楷体", 0, 20));//设置标题字体
            
            xyTemseries.add(i, factor);
            xyHumseries1.add(i, factor1);
            
            //更新折线图的数值
            try {
                Thread.currentThread();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if (i == 60){
                i=0;
                xyTemseries.delete(0, 59);
                xyHumseries1.delete(0, 59);
                continue;
            }
        }
    }
    
    /*
     * 创建折线图
     * */
    public static void createChart() {
    	Chart jz = new Chart();//新建Chart类为JFrame的子类
    	/*
    	 * 新建窗体
    	 * */
        JFrame frame0 = new JFrame();//新建JFrame窗体
        frame0.setSize(600, 400);//设置窗体尺寸大小
        frame0.getContentPane().add(jz.getTemJFreeChart());//将对象jz绘制的动态折线图添加到JFrame的面板上
//        frame0.getContentPane().add(jz.getTemJFreeChart(), BorderLayout.CENTER);//另外一种布局方式

        frame0.setVisible(true);//使得窗体对用户可见
        frame0.setLocationRelativeTo(null); // 窗口居于屏幕正中央
        frame0.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        dynamicRun();//绘制动态折线图的函数
        
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
    	createChart();//绘制并显示动态折线图
    }
}
