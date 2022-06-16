import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonMouseListener implements ActionListener { //添加节点
    public static ThemeLabel fatherLabel=null;//初始化设成根节点,每次点击完也设成根节点
    public int x=0,y=0;
    public static int nowmax=0;
    public static void setFatherLabel(ThemeLabel themeLabel){
        fatherLabel=themeLabel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        fatherLabel=Constent.fatherLabel;
        if(fatherLabel!=null){
            System.out.println("father"+fatherLabel.LabelName);
        }else{
            System.out.println("null");
        }
        int num=0,max=-10;
        System.out.println("why");
        if (fatherLabel == MainWindow.pan.getRootThemeLabel() || fatherLabel == null) {//如果是根节点的子节点
            x = MainWindow.pan.getRootThemeLabelRightX();
            num= MainWindow.pan.getRootThemeLabel().getChildNUm();
            if(num>0){
                max=MainWindow.pan.getRootThemeLabel().getChild(0).getThemeMidY();
                for(int i=0;i<num;i++){
                    if(max<MainWindow.pan.getRootThemeLabel().getChild(i).getThemeMidY()){
                        max=MainWindow.pan.getRootThemeLabel().getChild(i).getThemeMidY();
                    }
                }
            }
            if(nowmax<max){
                nowmax=max+80;
            }
            y = nowmax;
            ThemeLabel themeLabel = new ThemeLabel(x + 40, y);
            themeLabel.setBackground(new Color(250, 245, 228));
            themeLabel.setForeground(new Color(18, 91, 80));
            Border blackLine = BorderFactory.createLineBorder(new Color(18, 91, 80),5,true);
            themeLabel.setBorder(blackLine);
            themeLabel.setFont(new Font("微软雅黑",Font.BOLD,40));
            MainWindow.pan.add(themeLabel);
            /******** 增加父子节点关联 ************/
            themeLabel.setFather(MainWindow.pan.getRootThemeLabel());
            MainWindow.pan.getRootThemeLabel().addChild(themeLabel);
            MainWindow.pan.add(themeLabel);
            /******** 设置主题等级 ************/
            themeLabel.setRank(1);
            themeLabel.updateRankSize(themeLabel.getRank());
            MainWindow.pan.add(themeLabel);
            /******** 增加连线 ************/
            ConnectLine connectLine = new ConnectLine(MainWindow.pan.getRootThemeLabelRightX(), MainWindow.pan.getRootThemeLabelMidY(), themeLabel.getThemeLeftX(), themeLabel.getThemeMidY());
            MainWindow.pan.addConnectLine(themeLabel, connectLine);//入度和连接线,子节点只能有一个入度

        } else {
            int distance = fatherLabel.getThemeLeftX() - MainWindow.pan.getRootThemeLabelRightX();
            if (distance < 0) {
                x = fatherLabel.getThemeLeftX();
                num= fatherLabel.getChildNUm();
                if(num>0){
                    max=fatherLabel.getChild(0).getThemeMidY();
                    for(int i=0;i<num;i++){
                        if(max<fatherLabel.getChild(i).getThemeMidY()){
                            max=fatherLabel.getChild(i).getThemeMidY();
                        }
                    }
                }
                if(nowmax<max){
                    nowmax=max+40;
                }
                y = nowmax;
                ThemeLabel themeLabel = new ThemeLabel(x - 40, y);
                themeLabel.setBackground(new Color(248, 180, 0));
                themeLabel.setForeground(Color.white);
                themeLabel.setFont(new Font("微软雅黑",Font.BOLD,25));
                /******** 增加父子节点关联 ************/
                themeLabel.setFather(fatherLabel);
                fatherLabel.addChild(themeLabel);
                /******** 设置主题等级 ************/
                themeLabel.setRank(fatherLabel.getRank() + 1);
                themeLabel.updateRankSize(themeLabel.getRank());
                MainWindow.pan.add(themeLabel);
                /******** 增加父子节点连接线 ************/
                ConnectLine connectLine = new ConnectLine(fatherLabel.getThemeLeftX(), fatherLabel.getThemeMidY(), themeLabel.getThemeRightX(), themeLabel.getThemeMidY());
                MainWindow.pan.addConnectLine(themeLabel, connectLine);//入度和连接线,子节点只能有一个入度
            } else {
                x = fatherLabel.getThemeRightX();
                num= fatherLabel.getChildNUm();
                if(num>0){
                    max=fatherLabel.getChild(0).getThemeMidY();
                    for(int i=0;i<num;i++){
                        if(max<fatherLabel.getChild(i).getThemeMidY()){
                            max=fatherLabel.getChild(i).getThemeMidY();
                        }
                    }
                }
                if(nowmax<max){
                    nowmax=max+40;
                }
                y = nowmax;
                ThemeLabel themeLabel = new ThemeLabel(x + 40, y);
                themeLabel.setBackground(new Color(248, 180, 0));
                themeLabel.setForeground(Color.white);
                themeLabel.setFont(new Font("微软雅黑",Font.BOLD,25));
                /******** 增加父子节点关联 ************/
                themeLabel.setFather(fatherLabel);
                fatherLabel.addChild(themeLabel);
                /******** 设置主题等级 ************/
                themeLabel.setRank(fatherLabel.getRank() + 1);
                themeLabel.updateRankSize(themeLabel.getRank());
                MainWindow.pan.add(themeLabel);
                /******** 增加父子节点连接线 ************/
                ConnectLine connectLine = new ConnectLine(fatherLabel.getThemeRightX(), fatherLabel.getThemeMidY(), themeLabel.getThemeLeftX(), themeLabel.getThemeMidY());
                MainWindow.pan.addConnectLine(themeLabel, connectLine);//入度和连接线,子节点只能有一个入度
            }
            fatherLabel = null;
        }
    }

    static public ThemeLabel add(String text, int rank, int yPlus){
        int x,y;
        int xShift = 100;
        if (fatherLabel == MainWindow.pan.getRootThemeLabel() || fatherLabel == null) {//如果是根节点的子节点
            x = MainWindow.pan.getRootThemeLabelRightX();
            y = MainWindow.pan.getRootThemeLabelTopY();
            ThemeLabel themeLabel = new ThemeLabel(x + xShift, y + yPlus, text, rank);
            themeLabel.setBackground(new Color(250, 245, 228));
            themeLabel.setForeground(new Color(18, 91, 80));
            Border blackLine = BorderFactory.createLineBorder(new Color(18, 91, 80),5,true);
            themeLabel.setBorder(blackLine);
            themeLabel.setFont(new Font("微软雅黑",Font.BOLD,40));
            /******** 增加父子节点关联 ************/
            themeLabel.setFather(MainWindow.pan.getRootThemeLabel());
            MainWindow.pan.getRootThemeLabel().addChild(themeLabel);
            MainWindow.pan.add(themeLabel);
            /******** 设置主题等级 ************/
            themeLabel.setRank(1);
            themeLabel.updateRankSize(themeLabel.getRank());
            MainWindow.pan.add(themeLabel);
            /******** 增加连线 ************/
            ConnectLine connectLine = new ConnectLine(MainWindow.pan.getRootThemeLabelRightX(), MainWindow.pan.getRootThemeLabelMidY(), themeLabel.getThemeLeftX(), themeLabel.getThemeMidY());
            MainWindow.pan.addConnectLine(themeLabel, connectLine);//入度和连接线,子节点只能有一个入度
            return themeLabel;
        }
        else {
            int distance = fatherLabel.getThemeLeftX() - MainWindow.pan.getRootThemeLabelRightX();
            if (distance < 0) {
                x = fatherLabel.getThemeLeftX();
                y = fatherLabel.getThemeTopY();
                ThemeLabel themeLabel = new ThemeLabel(x - xShift, y + yPlus, text, rank);
                themeLabel.setBackground(new Color(248, 180, 0));
                themeLabel.setForeground(Color.white);
                themeLabel.setFont(new Font("微软雅黑",Font.BOLD,25));
                /******** 增加父子节点关联 ************/
                themeLabel.setFather(fatherLabel);
                fatherLabel.addChild(themeLabel);
                /******** 设置主题等级 ************/
                themeLabel.setRank(fatherLabel.getRank() + 1);
                themeLabel.updateRankSize(themeLabel.getRank());
                MainWindow.pan.add(themeLabel);
                /******** 增加父子节点连接线 ************/
                ConnectLine connectLine = new ConnectLine(fatherLabel.getThemeLeftX(), fatherLabel.getThemeMidY(), themeLabel.getThemeRightX(), themeLabel.getThemeMidY());
                MainWindow.pan.addConnectLine(themeLabel, connectLine);//入度和连接线,子节点只能有一个入度
                return themeLabel;
            }
            else {
                x = fatherLabel.getThemeRightX();
                y = fatherLabel.getThemeTopY();
                ThemeLabel themeLabel = new ThemeLabel(x + xShift, y + yPlus, text, rank);
                themeLabel.setBackground(new Color(248, 180, 0));
                themeLabel.setForeground(Color.white);
                themeLabel.setFont(new Font("微软雅黑",Font.BOLD,25));
                /******** 增加父子节点关联 ************/
                themeLabel.setFather(fatherLabel);
                fatherLabel.addChild(themeLabel);
                /******** 设置主题等级 ************/
                themeLabel.setRank(fatherLabel.getRank() + 1);
                themeLabel.updateRankSize(themeLabel.getRank());
                MainWindow.pan.add(themeLabel);
                /******** 增加父子节点连接线 ************/
                ConnectLine connectLine = new ConnectLine(fatherLabel.getThemeRightX(), fatherLabel.getThemeMidY(), themeLabel.getThemeLeftX(), themeLabel.getThemeMidY());
                MainWindow.pan.addConnectLine(themeLabel, connectLine);//入度和连接线,子节点只能有一个入度
                return themeLabel;
            }
        }
    }
}
