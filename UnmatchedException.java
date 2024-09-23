public class UnmatchedException extends Exception {
    public UnmatchedException (String message) {
        super(message);

    }

        public UnmatchedException() {
            super("passwords do not match");
        }
    }

