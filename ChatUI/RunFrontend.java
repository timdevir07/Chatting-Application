// public class RunFrontend {
//     public static void main(String[] args) {
//         try {
//             // Run User A's flow (RegisterPage)
//             ProcessBuilder userA = new ProcessBuilder("java", "RegisterPage");
//             userA.inheritIO(); // show output in same terminal
//             userA.start();

//             // Run User B's flow (RegisterPageB)
//             ProcessBuilder userB = new ProcessBuilder("java", "RegisterPageB");
//             userB.inheritIO(); // show output in same terminal
//             userB.start();

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }

//******************************/

// public class RunFrontend {
//     public static void main(String[] args) {
//         try {
//             // 🔹 Step 1: Start UserA socket server
//             ProcessBuilder userAProcess = new ProcessBuilder("java", "UserA");
//             userAProcess.inheritIO();
//             userAProcess.start();

//             // 🔹 Short delay to let server start
//             Thread.sleep(2000);

//             // 🔹 Step 2: Start UserB client
//             ProcessBuilder userBProcess = new ProcessBuilder("java", "UserB");
//             userBProcess.inheritIO();
//             userBProcess.start();

//             // 🔹 Optionally also start RegisterPage (UI flow), if needed
//             // ProcessBuilder registerA = new ProcessBuilder("java", "RegisterPage");
//             // registerA.inheritIO();
//             // registerA.start();

//             // ProcessBuilder registerB = new ProcessBuilder("java", "RegisterPageB");
//             // registerB.inheritIO();
//             // registerB.start();

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }


import java.io.IOException;

public class RunFrontend {

    public static void main(String[] args) {
        try {
            // Path to javac and java (update only if needed)
            String javaPath = "java"; // assuming java is added to PATH

            // 1. Launch RegisterPage (User A)
            ProcessBuilder userAReg = new ProcessBuilder(javaPath, "RegisterPage");
            userAReg.directory(new java.io.File("."));  // Current directory
            userAReg.start();

            // 2. Launch RegisterPageB (User B)
            ProcessBuilder userBReg = new ProcessBuilder(javaPath, "RegisterPageB");
            userBReg.directory(new java.io.File("."));
            userBReg.start();

            System.out.println("Both RegisterPage and RegisterPageB launched.");
        } catch (IOException e) {
            System.out.println(" Error launching windows: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

