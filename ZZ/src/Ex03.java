
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Ex03{
   
   public static int monthDay(int year, int month) {
      if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
         return 31;
      } else if (month == 4 || month == 6 || month == 9 || month == 11) {
         return 30;
      } else {
         if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return 29;
         } else {
            return 28;
         }
      }
   }
   
   public static void calender(HashMap<String, ArrayList<String>> listMap, int year, int month) {      
      System.out.println(" " + year + "�� " + month + "���� �޷�");      

      int sum = 0;

      for (int i = 1583; i < year; i++) {
         if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
            // �����̶��
            sum += 2;
         } else {
            // ����̶��
            sum += 1;
         }
      }

      int first = (sum + 6) % 7; // �Է��� year�� 1�� 1���� ����

      for (int j = 1; j < month; j++) {
         first += monthDay(year, j) % 7;
      }

      int day = first % 7; // �Է��� month�� 1���� ����
      int num = 1; // month�� �� ǥ��
      int max = monthDay(year, month); // �ش� month�� ������ �ִ� �� ��
      boolean start = false;

      System.out.println(" Sun  Mon  Tue  Wed  Thu  Fri  Sat ");

      loop: for (int row = 0; row <= 5; row++) {
         for (int column = 0; column <= 6; column++) {
            if (row == 0 && !start) {
               // �޷��� ù ��
               if (column == day) {
                  // ���� �Ͽ� �����ϸ�
                  start = true;
               } else {
                  // ���� �Ͽ� ���� ������ ����
                  System.out.print("     ");
                  continue;
               }
            }   
            
            // 10������ month, num�� listMap�� ����� month, day���� �ڸ��� ���̸� �ذ�(ex. 2�� 02�� �ڸ��� ����)
            String monthString = Integer.toString(month);
            String dayString = Integer.toString(num);
            
            if(month < 10) {
               monthString = "0" + Integer.toString(month);
            }
            
            if(num < 10) {
               dayString = "0" + Integer.toString(num);
            }
            
            String date = year + "-" + monthString + "-" + dayString;
            
            if (listMap.containsKey(date)) {
               // if plans exist
               System.out.printf("  %02d.", num);
            } else {
               // if plans not exist
               System.out.printf("  %02d ", num);
            }            
            
            num++;

            if (num > max) {
               // �ִ� �� ���� �����ϸ� break loop
               System.out.println("");
               break loop;
            }
         }
         System.out.println("");
      }

   }

   public static void main(String[] args) {
      @SuppressWarnings("resource")
      Scanner scanner = new Scanner(System.in);
      HashMap<String, ArrayList<String>> listMap = new HashMap<String, ArrayList<String>>();
         
      String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // ���� ��¥
      String[] toDate = today.split("-"); // ���� ��¥�� ��, ��, �Ϸ� ����
      calender(listMap, Integer.parseInt(toDate[0]), Integer.parseInt(toDate[1])); // ���� ��¥�� ���� �� ���� �޷� �ڵ� ���
      
      System.out.println("+---------------------------------+");   
      System.out.println("| 1. ���� ���");
      System.out.println("| 2. ���� �˻� �� ����");
      System.out.println("| 3. �޷� ����");
      System.out.println("| h. ����    q. ����");
      System.out.println("+---------------------------------+");

      while (true) {
         System.out.print("��� (1.���� ���  2.���� �˻� �� ����  3.�޷� ����  h.����  q.����)\n> ");
         char order = scanner.next().charAt(0); // choose menu
         scanner.nextLine();

         switch (order) {
         case '1':
            System.out.print("[���� ���] ��¥�� �Է��ϼ���.(ex.2020-01-01)\n> ");
            String theDate = scanner.nextLine();

            if (!listMap.containsKey(theDate)) {
               // ������ ������ ���ٸ�, �� Arraylist�� �߰��Ѵ�.
               ArrayList<String> emptyList = new ArrayList<String>();
               listMap.put(theDate, emptyList);
            }

            System.out.print("[���� ���] ������ �Է��ϼ���.\n> ");
            String theList = scanner.nextLine();

            ArrayList<String> existList = listMap.get(theDate);

            existList.add(theList);
            listMap.put(theDate, existList);
            break;

         case '2':
            System.out.print("[���� �˻�] ��¥�� �Է��ϼ���.(ex.2020-01-01)\n> ");
            String findDate = scanner.nextLine();

            if (listMap.containsKey(findDate)) {
               // �ش� ��¥�� ������ �ִٸ�
               ArrayList<String> schedule = listMap.get(findDate);

               System.out.printf("%d���� ������ �ֽ��ϴ�.\n", schedule.size());

               for (int i = 0; i < schedule.size(); i++) {
                  // �����ϴ� ���� ��ü ���
                  System.out.printf("%d.%s\n", i + 1, schedule.get(i));
               }

               while (true) {
                  System.out.print("������ �����Ͻðڽ��ϱ�?(��:1 / �ƴϿ�:2)\n> ");
                  String change = scanner.nextLine();

                  if (change.equals("1")) {
                     // ���� �����Ѵ�.
                     System.out.println("���� ��ϵǾ� �ִ� �����Դϴ�.");
                     for (int i = 0; i < schedule.size(); i++) {
                        // �����ϴ� ���� ��ü ���
                        System.out.printf("%d.%s\n", i + 1, schedule.get(i));
                     }

                     System.out.print("������ ������ ��ȣ�� �Է��ϼ���.(���ڸ� �Է�)\n> ");
                     int number = scanner.nextInt();
                     scanner.nextLine();

                     if (number - 1 >= schedule.size()) {
                        // index not exists
                        System.out.println("�� �� �Է��ϼ̽��ϴ�. �����ϴ� ������ �����ϼ���.");
                        continue;
                     } else {
                        // index exists
                        System.out.print("���� ������ �Է��ϼ���.\n> ");
                        String changeList = scanner.nextLine();

                        schedule.set(number - 1, changeList);
                        System.out.println("���������� ������ ����Ǿ����ϴ�.");
                     }
                  } else if (change.equals("2")) {
                     // ���� �������� �ʴ´�.
                     break;
                  } else {
                     // 1�̳� 2 �ܿ� ������ ���� �Է��ϸ�
                     System.out.println("�� �� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���.");
                  }
               }

            } else {
               // �ش� ��¥�� ������ ���ٸ�
               System.out.println("�ش� ��¥�� ������ �������� �ʽ��ϴ�.");
            }
            break;

         case '3':            
            System.out.print("�⵵�� �Է��ϼ���: ");
            int year = scanner.nextInt();
            System.out.print("���� �Է��ϼ���: ");
            int month = scanner.nextInt();
            System.out.println("");
            calender(listMap, year, month);
            break;

         case 'h':
            System.out.println("");
            System.out.println("<���� ���� ���α׷� ����>");
            System.out.println("(1. ���� ���)�� �����Ͻø� ������ ����ϰ� ���� ��¥�� ���� ������ �Է��Ͻø� �ش� ������ ����˴ϴ�.");
            System.out.println("(2. ���� �˻� �� ����)�� �����Ͻø� �˻��ϰ� ���� ��¥�� �Է��Ͻø� �ش� ��¥�� ���� �� ������ �����ݴϴ�. ���� ���� ���� �����մϴ�.");
            System.out.println("(3. �޷� ����)�� �����Ͻø� ���Ͻô� �⵵�� ���� �޷��� �����ݴϴ�.");
            System.out.println("(h. ����)�� �����Ͻø� ���� ���� ��ô� �� ������ �����ݴϴ�.");
            System.out.println("(q. ����)�� �����Ͻø� ���� ���� ���α׷��� ����˴ϴ�.");
            System.out.println("");
            break;

         case 'q':
            System.out.println("�����մϴ�. �̿��� �ּż� �����մϴ�.");
            System.exit(0);

         default:
            System.out.println("�� �� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���.");
         }
      }
   }

}
