package com.example.demo.dto;

public record ApiResponse<T>(String msg, T data) {


}
