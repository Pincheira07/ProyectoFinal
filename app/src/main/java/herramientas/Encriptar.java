package herramientas;

public class Encriptar {
    public static void main(String[] args) {
        String text = "Matias Pincheira";
        System.out.println(text);

        char[] chars = text.toCharArray();

        for(char c : chars){
            c+=2;
            System.out.println(c);
        }
    }
}