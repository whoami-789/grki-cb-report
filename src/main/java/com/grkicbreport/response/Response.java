package com.grkicbreport.response;

import lombok.*;

import java.util.List;

@Data
public class Response {
    private Result result;
    private Header header;
    private Answer answer;

    public Response() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Response;
    }

    // Геттеры и сеттеры
    @Data
    public static class Result {
        private String success;

        public Result() {
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Result;
        }

        // Геттеры и сеттеры
    }

    @Data
    public static class Header {
        private String query_id;
        private String inquire;
        private String respond;

        public Header() {
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Header;
        }

        // Геттеры и сеттеры
    }

    @Data
    public static class Answer {
        private List<Error> errors;
        private Identity identity;

        public Answer() {
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Answer;
        }

        // Геттеры и сеттеры
        @Data
        public static class Error {
            private String code;
            private String comment;

            public Error() {
            }

            protected boolean canEqual(final Object other) {
                return other instanceof Error;
            }

            // Геттеры и сеттеры
        }

        @Data
        public static class Identity {
            private String agreement_guid;
            private String agreement_id;
            private String claim_guid;
            private String claim_id;
            private String contract_guid;
            private String contract_id;

            public Identity() {
            }

            protected boolean canEqual(final Object other) {
                return other instanceof Identity;
            }

            // Геттеры и сеттеры
        }
    }
}
