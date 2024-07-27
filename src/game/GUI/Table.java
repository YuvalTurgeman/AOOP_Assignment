//package game.GUI;
//
//import game.entities.MobileEntity;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class Table extends JFrame {
//    JTable table;
//
//    Table(ArrayList<MobileEntity> active,ArrayList<MobileEntity> finish,ArrayList<MobileEntity> disabled,int max){
//        setTitle("MobileEntitys information");
//        setSize(new Dimension(500,300));
//        //setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//        Object [][] data=new Object[max][5];
//        int index = 0;
//        for (MobileEntity r:finish){
//            Object[] array= new Object[5];
//            array[0]=r.getName();
//            array[1]=r.getCurrentSpeed();
//            array[2]=r.getMaxSpeed();
//            array[3]=r.getCurrentX();
//            array[4]="YES";
//            data[index] = Arrays.copyOf(array, array.length);
//            index++;
//        }
//        for (MobileEntity r:active){
//            Object[] array= new Object[5];
//            array[0]=r.getName();
//            array[1]=r.getCurrentSpeed();
//            array[2]=r.getMaxSpeed();
//            array[3]=r.getCurrentX();
//            array[4]="NO";
//            data[index] = Arrays.copyOf(array, array.length);
//            index++;
//        }
//
//        for(MobileEntity r : disabled){
//            Object[] array= new Object[5];
//            array[0]=r.getName();
//            array[1]=r.getCurrentSpeed();
//            array[2]=r.getMaxSpeed();
//            array[3]=r.getCurrentX();
//            array[4]="Failed";
//            data[index] = Arrays.copyOf(array, array.length);
//            index++;
//        }
//        String[] column={"MobileEntity name","Current Speed","Max Speed","Current X","Finished"};
//        DefaultTableModel model=new DefaultTableModel(data,column);
//        table=new JTable(model);
//        add(new JScrollPane(table));
//        validate();
//
//
//
//
//
//
//
//
//
//
//    }
//}