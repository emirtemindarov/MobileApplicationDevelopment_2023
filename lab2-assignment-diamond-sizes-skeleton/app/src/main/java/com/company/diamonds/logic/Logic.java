package com.company.diamonds.logic;

import android.renderscript.ScriptGroup;

import com.company.diamonds.ui.OutputInterface;

import java.sql.Statement;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */

    public void process(int size) {

        // TODO -- add your code here
        // Высота ромба
        int romb_height = (size * 2) - 1;

        // Ширина ромба
        int romb_width = size * 2;

        // Верхняя часть рамки
        String result = concat("+", symbols("-", romb_width));
        result += "\n";

        // Использование замыкания. Шаблон построения от верхней части ромба (зависит от номера строки i)
        MyClosure firstHalf = (int i) -> {
            return concat(
                    "/",
                    symbols(getCurrentStringSign(i), (i - 1) * 2),
                    "\\"
            );
        };

        // Использование замыкания. Шаблон построения середины ромба (номер строки i совпадает с размером ромба)
        MyClosure midLine = (int i) -> {
            return concat("<",
                    symbols(getCurrentStringSign(i), (i - 1) * 2),
                    ">"
            );
        };

        // Использование замыкания. Шаблон построения нижней части ромба (зависит от номера строки i)
        MyClosure secondHalf = (int i) -> {
            return concat("\\",
                    symbols(getCurrentStringSign(i), ((size * 2 - i) - 1) * 2),
                    "/"
            );
        };

        // Сохранение текущего варианта замыкания
        MyClosure currentStatement;

        // Основной цикл построения ромба и боковых стенок рамки
        for (int i = 1; i <= romb_height; i++) {
            // Определение шаблона построения части ромба через замыкания (для верхней части ромба)
            if (i < size) {
                currentStatement = firstHalf;
            // Определение шаблона построения части ромба через замыкания (для средней линии)
            } else if (i == size) {
                currentStatement = midLine;
            // Определение шаблона построения части ромба через замыкания (для нижней части ромба)
            } else {
                currentStatement = secondHalf;
            }

            // Композиция строки
            result += concat(
                    "|",
                    concat(
                            // пробелы до и после ромба в текущей строке (строки вида "   " в начале и в конце)
                            symbols(" ", Math.abs(size - i)),
                            // часть ромба для текущей строки (строка вида "/====\")
                            currentStatement.resultFrom(i)
                    )
            );

            // Перенос строки
            result += "\n";
        }

        // Нижняя часть рамки
        result += concat("+", symbols("-", romb_width));

        // Вывод строки на экран
        mOut.print(result);
    }

    // Конструктор строки c одинаковыми левыми и правыми частями, а также со средней частью определенной длины
    private String concat(String sideStr, String midStr) {
        String result = sideStr + midStr + sideStr;
        return result;
    }

    // Конструктор строки c разными левыми и правыми частями, а также со средней частью определенной длины
    private String concat(String leftStr, String midStr, String rightStr) {
        String result = leftStr + midStr + rightStr;
        return result;
    }

    // Конструктор строки с определенным количеством символов
    private String symbols(String symbol, int size) {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += symbol;
        }
        return result;
    }

    // Получить символ для заполнения текущей строки внутри ромба
    private String getCurrentStringSign(int signCode) {
        if (signCode % 2 == 0) {
            return "-";
        } else {
            return "=";
        }
    }

    // Интерфейс для реализации замыкания
    interface MyClosure {
        // Получить результат выполнения замыкания
        public String resultFrom(int i);
    }
}
