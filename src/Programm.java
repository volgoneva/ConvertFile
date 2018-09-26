import java.io.*;
import java.util.*;
public class Programm {
    static String array1[];
    static int j = 0;
    static String fillIn1 = "\"\";\"\";\"-\";\"-\";\"0\";";
    static String fillIn2 = "\"\";\"0\";\"1\";\"\";\"\";\"\";";
    static String fillIn3 = "\"\";\"1\";\"\";\"\";\"\";";
    static String fillIn4 = "\"1\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"1\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"-\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";\"\";";
    public static void main(String[] args) throws IOException {
        int countString = 0;
        String pathToFileString;
        String fileNameString;
        Scanner pathToFile = new Scanner ( System.in );
        System.out.println ( "Введите директорию к исходному файлу (без названия файла)" );
        pathToFileString = pathToFile.nextLine ();
        Scanner fileName = new Scanner ( System.in );
        System.out.println ( "Введите полное название исходного файла с расширением" );
        fileNameString = fileName.nextLine ();
        File file = new File ( pathToFileString + fileNameString );
        Scanner scanner = new Scanner ( file, "windows-1251" );
        while (scanner.hasNextLine ()) {                              // Подсчет строк исходного файла
            scanner.nextLine ();
            countString++;}
        scanner.close ();
        Scanner scanner2 = new Scanner ( file, "windows-1251" );
        String array2[][] = new String[countString][18];
        while (scanner2.hasNextLine ()) {                             //Считываем весь файл
            String line = scanner2.nextLine ();
            array1 = line.split ( ";" );
            for (int i = 0; i < array1.length - 1; i++) {
                array2[j][i] = array1[i + 1];
            }
            j++;
        }
        scanner2.close ();
        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < 17; j++) {
                switch (j) {
                    case 0:
                        array2[i][j] = array2[i][j].substring ( 0, 17 );
                        break;
                    case 1:
                        array2[i][j] = "Госномер:" + array2[i][j];
                        break;
                    case 2:
                        array2[i][j] = "VIN:" + array2[i][j];
                        break;
                    case 5:
                        array2[i][j] = "Марка:" + array2[i][6] + "," + " Цвет:" + array2[i][j];
                        break;
                    case 6:
                        array2[i][j] = "250" + array2[i][3].substring ( 5, 19 );
                        break;
                }
            }
        }
        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < 17; j++) {
                switch (j) {
                    case 10:
                        array2[i][j] = array2[i][4];       // Перемещаем MSISDN
                        break;
                    case 11:
                        array2[i][j] = array2[i][0];       // Перемещаем время
                        break;
                    case 12:
                        array2[i][j] = array2[i][5];       // Перемещаем Марка
                        break;
                    case 13:
                        array2[i][j] = array2[i][2];       // Перемещаем VIN
                        break;
                    case 14:
                        array2[i][j] = array2[i][1];       // Перемещаем гос. номер
                        break;
                    case 15:
                        array2[i][j] = array2[i][3];       // Перемещаем ICCID
                        break;
                    case 16:
                        array2[i][j] = array2[i][6];       // Перемещаем IMSI
                        break;
                }
            }
        }

        FileWriter fw = new FileWriter ( pathToFileString + "convert.csv" );  //Записываем в файл
        for (int i = 0; i < array2.length; i++) {
            for (int j = 10; j < 17; j++) {
                switch (j) {                                                           //Заполняем пустые поля
                    case 10:
                        fw.write ( "\"" + array2[i][j] + "\"" + ";" + fillIn1);
                        break;
                    case 11:
                        fw.write ( "\"" + array2[i][j] + "\"" + ";" + fillIn2);
                        break;
                    case 12:
                        fw.write ( "\"" + array2[i][j] + "\"" + ";" + fillIn3);
                        break;
                    case 14:
                        fw.write ( "\"" + array2[i][j] + "\"" + ";" + fillIn4);
                        break;
                    case 16:
                        fw.write ( "\"" + array2[i][j] + "\"");
                        break;
                    default:
                        fw.write ( "\"" + array2[i][j] + "\"" + ";" );
                }
            }
            fw.write ("\n"  );
        }
        fw.close ();
        System.out.println ( "Исходный фай " + fileNameString + " успешно конвертирован  в convert.csv в директории " + pathToFileString );
    }
}