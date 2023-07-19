import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Letter {
    A('A', "1111;1001;1111;1001;1001"),
    B('B', "1111;1001;1110;1001;1111"),
    C('C', "1111;1000;1000;1000;1111"),
    D('D', "1110;1001;1001;1001;1110"),
    E('E', "1111;1000;1110;1000;1111"),
    F('F', "1111;1000;1110;1000;1000"),
    G('G', "1111;1000;1011;1001;1111"),
    H('H', "1001;1001;1111;1001;1001"),
    I('I', "111;010;010;010;111"),
    J('J', "0111;0010;0010;1010;1110"),
    K('K', "1001;1001;1110;1001;1001"),
    L('L', "1000;1000;1000;1000;1111"),
    M('M', "10001;11011;10101;10001;10001"),
    N('N', "1001;1101;1011;1001;1001"),
    O('O', "1111;1001;1001;1001;1111"),
    P('P', "1111;1001;1111;1000;1000"),
    Q('Q', "11110;10010;10010;10010;11110;00001"),
    R('R', "1111;1001;1110;1001;1001"),
    S('S', "01111;10000;01110;00001;11110"),
    T('T', "111;010;010;010;010"),
    U('U', "1001;1001;1001;1001;1111"),
    V('V', "10001;10001;10001;01010;00100"),
    W('W', "10001;10001;10101;11011;10001"),
    X('X', "10001;01010;00100;01010;10001"),
    Y('Y', "10001;01010;00100;00100;00100"),
    Z('Z', "1111;0001;0110;1000;1111"),
    DASH('-', "000;000;111;000;000");


    private char character;
    private int width;
    private boolean[][] pattern;

    Letter(char character, String pattern) {
        this.character = character;

        this.pattern = Arrays.stream(pattern.split(";")).map(String::toCharArray).map(a -> {
            boolean[] b = new boolean[a.length];
            for(int i = 0; i < a.length; i++) {
                b[i] = a[i] != '0';
            }
            return b;
        }).toArray(boolean[][]::new);

        width = this.pattern[0].length;
    }

    public boolean[][] getPattern() {
        return pattern;
    }

    public int getWidth() {
        return width;
    }

    public char getCharacter() {
        return character;
    }

    private static Map<Character, Letter> map = new HashMap<>();

    static {
        for(Letter letter : Letter.values()) {
            map.put(letter.character, letter);
        }
    }

    public static Letter get(char c) {
        if(c >= 97 && c <= 122) {
            c = (char) (c - 32);
        }
        return map.getOrDefault(c, A);
    }

}
