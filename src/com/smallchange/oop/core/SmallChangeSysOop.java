package com.smallchange.oop.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static com.smallchange.oop.constant.ChoiceConstant.*;

/**
 * @author WS
 */
public class SmallChangeSysOop {
    /**
     * 输入对象
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * 控制循环
     */
    private boolean loop = true;

    /**
     * 明细
     * String -> StringBuilder
     */
    private final StringBuilder details = new StringBuilder("-------------零钱通明细-------------");
    // 3 收益入账
    /**
     * 余额
     */
    private double balance = 0;

    /**
     * 日期格式化规范
     */
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");

    /**
     * 菜单展示
     */
    public void mainMenu() {
        do {
            System.out.println("\n=============零钱通菜单选择=============");
            System.out.println("\t\t\t1 零钱通明细");
            System.out.println("\t\t\t2 收益入账");
            System.out.println("\t\t\t3 消 费");
            System.out.println("\t\t\t4 退 出");
            System.out.print("请选择(1 ~ 4) : ");
            String key = scanner.next();

            //使用 switch 分支
            switch (key) {
                case DETAIL:
                    this.detail();
                    break;
                case INCOME:
                    this.income();
                    break;
                case PAY:
                    this.pay();
                    break;
                case EXIT:
                    this.exit();
                    break;
                default:
                    System.out.println("选择有误 , 请重新选择");
            }
        } while (loop);
    }

    /**
     * 展示零钱通明细
     */
    private void detail() {
        System.out.println(details);
    }

    /**
     * 零钱入账
     */
    private void income() {
        System.out.print("收益入账金额 : ");
        double income = scanner.nextDouble();
        //money需要进行校验
        if (income <= 0) {
            System.out.print("收益金额异常!");
            return;
        }
        System.out.print("收益说明 : ");
        String incomeNote = scanner.next();
        balance += income;
        Date date = new Date();
        details.append("\n").append(incomeNote)
                .append("\t+").append(income)
                .append("\t").append(sdf.format(date))
                .append("\t").append("余额 ").append(balance);
    }

    /**
     * 零钱支出
     */
    private void pay() {
        System.out.print("消费金额 : ");
        double cost = scanner.nextDouble();
        if (cost > balance || cost < 0) {
            System.out.print("消费金额异常!重新选择");
            return;
        }
        System.out.print("消费说明 : ");
        String costNote = scanner.next();
        balance -= cost;
        Date date = new Date();
        details.append("\n").append(costNote)
                .append("\t-").append(cost)
                .append("\t").append(sdf.format(date))
                .append("\t").append("余额 ").append(balance);
    }

    /**
     * 退出零钱通
     */
    private void exit() {
        String choice = scanner.next();
        while (!(YES.equals(choice) || NO.equals(choice))) {
            System.out.println("确认退出 ? (yes--确认退出 , no--重新选择)");
            choice = scanner.next();
        }
        //判断是否退出
        if (YES.equalsIgnoreCase(choice)) {
            loop = false;
        }
    }
}
