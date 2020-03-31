package elements;

public class MailObject {
    private int position;
    private String mail = "<Не указано>";
    private String subject = "<Без темы>";
    private String mailMsg = "";

    private MailObject() {

    }

    public int getPosition() {
        return position;
    }

    public String getMail() {
        return mail;
    }

    public String getSubject() {
        return subject;
    }

    public String getMailMsg() {
        return mailMsg;
    }

    public static Builder newBuilder() {
        return new MailObject().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setPosition(int position) {
            MailObject.this.position = position;
            return this;
        }

        public Builder setMail(String mail) {
            MailObject.this.mail = mail;
            return this;
        }

        public Builder setSubject(String subject) {
            MailObject.this.subject = subject;
            return this;
        }

        public Builder setMessage(String msg) {
            MailObject.this.mailMsg = msg;
            return this;
        }

        public MailObject build() {
            return MailObject.this;
        }
    }
}
