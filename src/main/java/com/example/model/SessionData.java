package com.example.model;

public class SessionData {
    private String _databaseName;
    private String _port;
    private String _user;
    private String _password;

    public String get_databaseName() {
        return _databaseName;
    }

    public void set_databaseName(String _databaseName) {
        this._databaseName = _databaseName;
    }

    public String get_port() {
        return _port;
    }

    public void set_port(String _port) {
        this._port = _port;
    }

    public String get_user() {
        return _user;
    }

    public void set_user(String _user) {
        this._user = _user;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
