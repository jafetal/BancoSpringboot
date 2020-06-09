package com.example.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class fechaService {
	public static Date getCurrentTimeUsingDate() {
        Calendar cal = Calendar.getInstance();
	    return cal.getTime();
	}
}
