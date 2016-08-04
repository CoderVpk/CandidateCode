package com.main;

import javax.script.ScriptException;

import com.test1.CandidateCode;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input1 = "NFNpp";
		try {
			String result = CandidateCode.iteration(input1);
			System.out.println(result);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
