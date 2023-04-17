import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final String CMD_PATTERN =  "^[0-9a-zA-Z\\s]*$";
    public static final String MESSAGE_PREFIX = "[SHARETREATS] : ";
    public static HashSet<String> productCodes;
    public static Map<String, String> exchangeProducts = new HashMap<>();
    public static String productCode = "";
    public static String shopCode = "";
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input;
        productCodes = getProductCodes();

        while(true){
            input = new StringTokenizer(br.readLine()," ");

            String cmd = input.nextToken();
            if(!isValidCommand(cmd)){
                System.out.println("Invalid input");
                continue;
            }

            switch (cmd.toUpperCase()){

                case "CHECK" :
                    isProductCode(input);
                    break;
                case "HELP" :
                    System.out.println(MESSAGE_PREFIX + "HELP");
                    break;
                case "CLAIM" :
                    if(!isShopCode(input.nextToken())) break;
                    if(!isProductCode(input)) break;
                    exchangeProduct();
                    break;
                case "EXIT" :
                    System.exit(0);

                default:
                    System.out.println(MESSAGE_PREFIX + "command does not exist");
            }
        }
    }

    public static void exchangeProduct() {
        productCodes.remove(productCode);
        exchangeProducts.put(productCode, shopCode);
        System.out.println(MESSAGE_PREFIX + "Product exchange has been completed");
    }

    public static HashSet<String> getProductCodes() {
        HashSet<String> productCodes = new HashSet<>();
        Random random = new Random();

        while (productCodes.size() < 20) {
            StringBuilder code = new StringBuilder();

            for (int i = 0; i < 9; i++) {
                int digit = random.nextInt(10);
                code.append(digit);
            }

            productCodes.add(code.toString());
        }

        productCodes.add("111111111");

        return productCodes;
    }

    public static boolean isProductCode(StringTokenizer input) {
        StringBuilder productCode = new StringBuilder();
        if(input == null) return false;

        while(input.hasMoreTokens()){
            productCode.append(input.nextToken());
        }

        if (productCode.length() != 9) {
            System.out.println(MESSAGE_PREFIX + "Please check the product code");
            return false;
        }

        return isAvailableProductCode(productCode.toString());
    }
    public static boolean isAvailableProductCode(String productCode){
        Main.productCode = productCode;
        boolean isAvailable = productCodes.contains(productCode);
        String msg = isAvailable ? "This is a product code that can be exchanged" : "This product code cannot be exchanged";
        System.out.println(MESSAGE_PREFIX + msg);
        return isAvailable;
    }

    public static boolean isShopCode(String shopCode){
        String shopCodePattern = "^[A-Za-z]{6}$";

        if(!shopCode.matches(shopCodePattern)){
            System.out.println(MESSAGE_PREFIX + "Invalid store code");
            return false;
        }
        Main.shopCode = shopCode;
        return true;
    }

    public static boolean isValidCommand(String cmd) {
        return cmd.matches(CMD_PATTERN);
    }
}