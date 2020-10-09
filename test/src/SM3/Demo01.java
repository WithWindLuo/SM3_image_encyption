package SM3;

public class Demo01 {
    public static void main(String[] args) {
        String hash_value = SM3.hash(String.valueOf(0.1548948646545646));
        System.out.println(hash_value);
        hash_value = SM3.hash(String.valueOf(1.154894834534234634633246646545647));
        System.out.println(hash_value);
    }
}
