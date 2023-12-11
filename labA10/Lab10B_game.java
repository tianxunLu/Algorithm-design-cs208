//package labA10;
//
//import labA11.QReader;
//import labA11.QWriter;
//
//public class Lab10B_game {
//    public static void main(String[] args) {
//        QReader input = new QReader();
//        QWriter out = new QWriter();
////        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//
//        for (int i = 0; i < n; i++) {
//            String s = input.next();
//            int length = s.length();
//
//            // Alice:3 Draw:2 Bob:1
//            int[][] matrix = new int[length][length];
//
//            // base cases: only 2 character
//            for (int j = 0; j < length-1; j++) {
//                if (s.charAt(j) == s.charAt(j+1)) matrix[j][j+1] = 2;
//                else matrix[j][j+1] = 3;
//            }
//
//            // Dynamic programming
//            int num = 4; // from 4 character
//            while (num <= length){ // to full character
//                int index = 0; // first index
//                while (index <= length-num){
//                    int resultLL, resultLR, resultRL, resultRR;
//
//                    // Alice choose left, Bob choose left
//                        // case 1: compare char1, char2
//                    if (s.charAt(index) < s.charAt(index+1)) resultLL = 3;
//                    else if (s.charAt(index) > s.charAt(index+1)) resultLL = 1;
//                    else resultLL = matrix[index+2][index+num-1];
//
//                    // Alice choose left, Bob choose right
//                        // case 2: compare char1, char4
//                    if (s.charAt(index) < s.charAt(index+num-1)) resultLR = 3;
//                    else if (s.charAt(index) > s.charAt(index+num-1)) resultLR = 1;
//                    else resultLR = matrix[index+1][index+num-2];
//
//                    // Alice choose right, Bob choose left
//                        // case 3: compare char4, char1
//                    if (s.charAt(index+num-1) < s.charAt(index)) resultRL = 3;
//                    else if (s.charAt(index+num-1) > s.charAt(index)) resultRL = 1;
//                    else resultRL = matrix[index+1][index+num-2];
//
//                    // Alice choose right, Bob choose right
//                        // case 1: compare char4, char3
//                    if (s.charAt(index+num-1) < s.charAt(index+num-2)) resultRR = 3;
//                    else if (s.charAt(index+num-1) > s.charAt(index+num-2)) resultRR = 1;
//                    else resultRR = matrix[index][index+num-3];
//
//                    // Bob is clever
//                    int resultL = Math.min(resultLL,resultLR);
//                    int resultR = Math.min(resultRL,resultRR);
//
//                    // Alice is clever
//                    matrix[index][index+num-1] = Math.max(resultL,resultR);
//
//                    index++;
//                }
//                num += 2;
//            }
//            if (matrix[0][length-1] == 3) out.println("Alice");
//            else if (matrix[0][length-1] == 2) out.println("Draw");
//            else if (matrix[0][length-1] == 1) out.println("Bob");
//            else out.println("Error");
//        }
//        out.close();
//    }
//}
