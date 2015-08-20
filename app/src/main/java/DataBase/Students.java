package DataBase;

public class Students {

    private int _id;
    private String _name;
    private String _regNo;
    private String _phoneNo;
    private String _email;
    private String _birthday;
    private String _gender;
    private String _gender_id;
    private String _department;

    private String _department_id;

    public Students(String name, String regNo, String phoneNo, String email, String _birthday, String gender, String gender_id, String department, String department_id) {
        this._name = name;
        this._regNo = regNo;
        this._phoneNo = phoneNo;
        this._email = email;
        this._birthday = _birthday;
        this._gender = gender;
        this._gender_id = gender_id;
        this._department = department;
        this._department_id = department_id;
    }


    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_regNo() {
        return _regNo;
    }

    public String get_phoneNo() {
        return _phoneNo;
    }

    public String get_email() {
        return _email;
    }

    public String get_birthday() {
        return _birthday;
    }

    public String get_gender() {
        return _gender;
    }

    public String get_gender_id() {
        return _gender_id;
    }

    public String get_department() {
        return _department;
    }

    public String get_department_id() {
        return _department_id;
    }

}
