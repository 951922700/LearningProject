package com.lyl.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FFMpegTest {

	private String ffmpegEXE;
	
	public FFMpegTest(String ffmpegEXE) {
		super();
		this.ffmpegEXE = ffmpegEXE;
	}


	
	public void convertor(String videoInputPath, String videoOutputPath) throws Exception {
		//ffmpeg -i iron-man.mp4 -vcodec copy -an iron.mp4 消除原视频声音
		//ffmpeg -i mp4 -i mp3 -t 10 -y result.mp4  合并
//		ffmpeg -i input.mp4 -y output.avi
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);
		
		command.add("-i");
		command.add(videoInputPath);
		command.add("-y");
		command.add(videoOutputPath);
		
		for (String c : command) {
			System.out.print(c + " ");
		}
		
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		
		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		
		String line = "";
		while ( (line = br.readLine()) != null ) {
		}
		
		if (br != null) {
			br.close();
		}
		if (inputStreamReader != null) {
			inputStreamReader.close();
		}
		if (errorStream != null) {
			errorStream.close();
		}
		
	}

	public static void main(String[] args) {
		FFMpegTest ffmpeg = new FFMpegTest("E:\\ffmpeg-4.3.1-win64-static\\bin\\ffmpeg.exe");
		try {
			ffmpeg.convertor("F:\\videos_upload_file\\200811G0KT1YRYFW\\video\\1.mp4", "F:\\videos_upload_file\\200811G0KT1YRYFW\\video\\猫猫.avi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
