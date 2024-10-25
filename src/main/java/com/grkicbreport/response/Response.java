package com.grkicbreport.response;

import java.util.List;

public class Response {
    private Result result;
    private Header header;
    private Answer answer;

    public Response() {
    }

    public Result getResult() {
        return this.result;
    }

    public Header getHeader() {
        return this.header;
    }

    public Answer getAnswer() {
        return this.answer;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Response)) return false;
        final Response other = (Response) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$result = this.getResult();
        final Object other$result = other.getResult();
        if (this$result == null ? other$result != null : !this$result.equals(other$result)) return false;
        final Object this$header = this.getHeader();
        final Object other$header = other.getHeader();
        if (this$header == null ? other$header != null : !this$header.equals(other$header)) return false;
        final Object this$answer = this.getAnswer();
        final Object other$answer = other.getAnswer();
        if (this$answer == null ? other$answer != null : !this$answer.equals(other$answer)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Response;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $result = this.getResult();
        result = result * PRIME + ($result == null ? 43 : $result.hashCode());
        final Object $header = this.getHeader();
        result = result * PRIME + ($header == null ? 43 : $header.hashCode());
        final Object $answer = this.getAnswer();
        result = result * PRIME + ($answer == null ? 43 : $answer.hashCode());
        return result;
    }

    public String toString() {
        return "Response(result=" + this.getResult() + ", header=" + this.getHeader() + ", answer=" + this.getAnswer() + ")";
    }

    // Геттеры и сеттеры
    public static class Result {
        private String success;

        public Result() {
        }

        public String getSuccess() {
            return this.success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Result)) return false;
            final Result other = (Result) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$success = this.getSuccess();
            final Object other$success = other.getSuccess();
            if (this$success == null ? other$success != null : !this$success.equals(other$success)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Result;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $success = this.getSuccess();
            result = result * PRIME + ($success == null ? 43 : $success.hashCode());
            return result;
        }

        public String toString() {
            return "Response.Result(success=" + this.getSuccess() + ")";
        }
        // Геттеры и сеттеры
    }

    public static class Header {
        private String query_id;
        private String inquire;
        private String respond;

        public Header() {
        }

        public String getQuery_id() {
            return this.query_id;
        }

        public String getInquire() {
            return this.inquire;
        }

        public String getRespond() {
            return this.respond;
        }

        public void setQuery_id(String query_id) {
            this.query_id = query_id;
        }

        public void setInquire(String inquire) {
            this.inquire = inquire;
        }

        public void setRespond(String respond) {
            this.respond = respond;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Header)) return false;
            final Header other = (Header) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$query_id = this.getQuery_id();
            final Object other$query_id = other.getQuery_id();
            if (this$query_id == null ? other$query_id != null : !this$query_id.equals(other$query_id)) return false;
            final Object this$inquire = this.getInquire();
            final Object other$inquire = other.getInquire();
            if (this$inquire == null ? other$inquire != null : !this$inquire.equals(other$inquire)) return false;
            final Object this$respond = this.getRespond();
            final Object other$respond = other.getRespond();
            if (this$respond == null ? other$respond != null : !this$respond.equals(other$respond)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Header;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $query_id = this.getQuery_id();
            result = result * PRIME + ($query_id == null ? 43 : $query_id.hashCode());
            final Object $inquire = this.getInquire();
            result = result * PRIME + ($inquire == null ? 43 : $inquire.hashCode());
            final Object $respond = this.getRespond();
            result = result * PRIME + ($respond == null ? 43 : $respond.hashCode());
            return result;
        }

        public String toString() {
            return "Response.Header(query_id=" + this.getQuery_id() + ", inquire=" + this.getInquire() + ", respond=" + this.getRespond() + ")";
        }
        // Геттеры и сеттеры
    }

    public static class Answer {
        private List<Error> errors;
        private Identity identity;

        public Answer() {
        }

        public List<Error> getErrors() {
            return this.errors;
        }

        public Identity getIdentity() {
            return this.identity;
        }

        public void setErrors(List<Error> errors) {
            this.errors = errors;
        }

        public void setIdentity(Identity identity) {
            this.identity = identity;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Answer)) return false;
            final Answer other = (Answer) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$errors = this.getErrors();
            final Object other$errors = other.getErrors();
            if (this$errors == null ? other$errors != null : !this$errors.equals(other$errors)) return false;
            final Object this$identity = this.getIdentity();
            final Object other$identity = other.getIdentity();
            if (this$identity == null ? other$identity != null : !this$identity.equals(other$identity)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Answer;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $errors = this.getErrors();
            result = result * PRIME + ($errors == null ? 43 : $errors.hashCode());
            final Object $identity = this.getIdentity();
            result = result * PRIME + ($identity == null ? 43 : $identity.hashCode());
            return result;
        }

        public String toString() {
            return "Response.Answer(errors=" + this.getErrors() + ", identity=" + this.getIdentity() + ")";
        }

        // Геттеры и сеттеры
        public static class Error {
            private String code;
            private String comment;

            public Error() {
            }

            public String getCode() {
                return this.code;
            }

            public String getComment() {
                return this.comment;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Error)) return false;
                final Error other = (Error) o;
                if (!other.canEqual((Object) this)) return false;
                final Object this$code = this.getCode();
                final Object other$code = other.getCode();
                if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
                final Object this$comment = this.getComment();
                final Object other$comment = other.getComment();
                if (this$comment == null ? other$comment != null : !this$comment.equals(other$comment)) return false;
                return true;
            }

            protected boolean canEqual(final Object other) {
                return other instanceof Error;
            }

            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                final Object $code = this.getCode();
                result = result * PRIME + ($code == null ? 43 : $code.hashCode());
                final Object $comment = this.getComment();
                result = result * PRIME + ($comment == null ? 43 : $comment.hashCode());
                return result;
            }

            public String toString() {
                return "Response.Answer.Error(code=" + this.getCode() + ", comment=" + this.getComment() + ")";
            }
            // Геттеры и сеттеры
        }

        public static class Identity {
            private String agreement_guid;
            private String agreement_id;
            private String claim_guid;
            private String claim_id;
            private String contract_guid;
            private String contract_id;

            public Identity() {
            }

            public String getAgreement_guid() {
                return this.agreement_guid;
            }

            public String getAgreement_id() {
                return this.agreement_id;
            }

            public String getClaim_guid() {
                return this.claim_guid;
            }

            public String getClaim_id() {
                return this.claim_id;
            }

            public String getContract_guid() {
                return this.contract_guid;
            }

            public String getContract_id() {
                return this.contract_id;
            }

            public void setAgreement_guid(String agreement_guid) {
                this.agreement_guid = agreement_guid;
            }

            public void setAgreement_id(String agreement_id) {
                this.agreement_id = agreement_id;
            }

            public void setClaim_guid(String claim_guid) {
                this.claim_guid = claim_guid;
            }

            public void setClaim_id(String claim_id) {
                this.claim_id = claim_id;
            }

            public void setContract_guid(String contract_guid) {
                this.contract_guid = contract_guid;
            }

            public void setContract_id(String contract_id) {
                this.contract_id = contract_id;
            }

            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Identity)) return false;
                final Identity other = (Identity) o;
                if (!other.canEqual((Object) this)) return false;
                final Object this$agreement_guid = this.getAgreement_guid();
                final Object other$agreement_guid = other.getAgreement_guid();
                if (this$agreement_guid == null ? other$agreement_guid != null : !this$agreement_guid.equals(other$agreement_guid))
                    return false;
                final Object this$agreement_id = this.getAgreement_id();
                final Object other$agreement_id = other.getAgreement_id();
                if (this$agreement_id == null ? other$agreement_id != null : !this$agreement_id.equals(other$agreement_id))
                    return false;
                final Object this$claim_guid = this.getClaim_guid();
                final Object other$claim_guid = other.getClaim_guid();
                if (this$claim_guid == null ? other$claim_guid != null : !this$claim_guid.equals(other$claim_guid))
                    return false;
                final Object this$claim_id = this.getClaim_id();
                final Object other$claim_id = other.getClaim_id();
                if (this$claim_id == null ? other$claim_id != null : !this$claim_id.equals(other$claim_id))
                    return false;
                final Object this$contract_guid = this.getContract_guid();
                final Object other$contract_guid = other.getContract_guid();
                if (this$contract_guid == null ? other$contract_guid != null : !this$contract_guid.equals(other$contract_guid))
                    return false;
                final Object this$contract_id = this.getContract_id();
                final Object other$contract_id = other.getContract_id();
                if (this$contract_id == null ? other$contract_id != null : !this$contract_id.equals(other$contract_id))
                    return false;
                return true;
            }

            protected boolean canEqual(final Object other) {
                return other instanceof Identity;
            }

            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                final Object $agreement_guid = this.getAgreement_guid();
                result = result * PRIME + ($agreement_guid == null ? 43 : $agreement_guid.hashCode());
                final Object $agreement_id = this.getAgreement_id();
                result = result * PRIME + ($agreement_id == null ? 43 : $agreement_id.hashCode());
                final Object $claim_guid = this.getClaim_guid();
                result = result * PRIME + ($claim_guid == null ? 43 : $claim_guid.hashCode());
                final Object $claim_id = this.getClaim_id();
                result = result * PRIME + ($claim_id == null ? 43 : $claim_id.hashCode());
                final Object $contract_guid = this.getContract_guid();
                result = result * PRIME + ($contract_guid == null ? 43 : $contract_guid.hashCode());
                final Object $contract_id = this.getContract_id();
                result = result * PRIME + ($contract_id == null ? 43 : $contract_id.hashCode());
                return result;
            }

            public String toString() {
                return "Response.Answer.Identity(agreement_guid=" + this.getAgreement_guid() + ", agreement_id=" + this.getAgreement_id() + ", claim_guid=" + this.getClaim_guid() + ", claim_id=" + this.getClaim_id() + ", contract_guid=" + this.getContract_guid() + ", contract_id=" + this.getContract_id() + ")";
            }

            // Геттеры и сеттеры
        }
    }
}
