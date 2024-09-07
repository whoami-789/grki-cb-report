package com.grkicbreport.response;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private Result result;
    private Header header;
    private Answer answer;

    // Геттеры и сеттеры
    @Data
    public static class Result {
        private String success;
        // Геттеры и сеттеры
    }

    @Data
    public static class Header {
        private String query_id;
        private String inquire;
        private String respond;
        // Геттеры и сеттеры
    }

    @Data
    public static class Answer {
        private List<Error> errors;
        private Identity identity;

        // Геттеры и сеттеры
        @Data
        public static class Error {
            private String code;
            private String comment;
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

            // Геттеры и сеттеры
        }
    }
}
