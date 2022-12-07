
import java.util.Scanner;


public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static Boolean isRomul = false;

    public static void main (String[] args) throws Exception {
        System.out.println("Введите действие");
        String input="";
        Boolean check=false;
        try {
            input = getInput();
            check = validate(input);
        }catch (Exception e){
            System.err.printf(e.getMessage());
        }
        String[] arr;
        if(check){

            arr = stringToArr(input);
            String out = calculate(arr,input);
            if(isRomul){
                try {
                    System.out.printf(input+"="+romulOut(out));
                }catch (Exception e){
                    System.err.printf(e.toString());
                }
            }else{
                System.out.printf(input+"="+out);
            }
        }


        String [] act = {"+", "-", "/", "*"};
        String [] rAct = new String[]{"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        String ex = scn.nextLine();


        int actIndex = -1;
        for(int i = 0; i < act.length; i++){
            if(ex.contains(act[i])) {
                actIndex = i;
                break;
            }

        }
        if (actIndex == -1){
            throw new Exception();
        }


        String[] d = ex.split(rAct[actIndex]);
        int a,b;
        a = Integer.parseInt(d[0]);
        b = Integer.parseInt(d[1]);
        if(a<1 || a>10){
            throw  new Exception();
        }


        if(b<1 || b>10) {
            throw  new Exception();
        }


        int res;
        switch (rAct[actIndex]){
            case"\\+":
                res = a + b;
                break;
            case "-":
                res = a - b;
                break;
            case "/":
                res = a / b;
                break;
            default:
                res = a * b;
        }
        System.out.println(res);

    }

    public static String getInput() throws Exception{
        String input = scan.next();
        if(input.length()>6){
            throw new Exception("Выражение введено некорректно");
        }
        return input;
    }

    public static Boolean validate (String str)throws Exception{

        String romulStr = "I II III IV V VI VII VIII IX X";
        String[] output = str.split("[+-/*]",2);

        Boolean check = false;
        for (String it:output) {

            if(!(romulStr.contains(it)&&(romulStr.contains(output[0])|romulStr.contains(output[1])))) {
                try {
                    int num = Integer.parseInt(it);
                    if(num<11&&num>0){
                        check = true;
                        isRomul = false;
                    }

                    else throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.err.println("Выражение введено некорректно");
                }
            } else if (romulStr.contains(it)) {
                check=true;
                isRomul = true;
            }else{
                throw new Exception("Выражение введено некорректно");
            }
        }

        return check;
    }

    public static  String[] stringToArr(String str){
        String[] exchangeArr = str.split("[+-/*]",2);
        for (int i=0;i<exchangeArr.length;i++) {
            if(isRomul) {
                String log = Roman.valueOf(exchangeArr[i]).getNum();
                exchangeArr[i] = log;
            }
        }
        return exchangeArr;
    }

    public static String calculate(String[] arr, String input){
        if(input.contains("/")){
            int op = Opr.DEL.action(arr[0],arr[1]);
            return Integer.toString(op);
        } else if (input.contains("*")) {
            int op = Opr.UMN.action(arr[0],arr[1]);
            return Integer.toString(op);
        } else if (input.contains("+")) {
            int op = Opr.PLUS.action(arr[0],arr[1]);
            return Integer.toString(op);
        } else if (input.contains("-")) {
            int op = Opr.MINUS.action(arr[0],arr[1]);
            return Integer.toString(op);
        }
        return null;
    }

    public static String romulOut(String num) throws Exception{
        int lock = Integer.parseInt(num);
        if(lock<0) throw new Exception();
        String[] romanNumbers = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arab = { 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (lock > 0 || arab.length == (i - 1)) {
            while ((lock - arab[i]) >= 0) {
                lock -= arab[i];
                result.append(romanNumbers[i]);
            }
            i++;
        }
        return result.toString();
    }
}