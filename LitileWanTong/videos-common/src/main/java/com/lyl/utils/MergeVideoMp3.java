package com.lyl.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {

	private String ffmpegEXE;
	
	public MergeVideoMp3(String ffmpegEXE) {
		super();
		this.ffmpegEXE = ffmpegEXE;
	}

	/**
	 * 1.消除原视频音轨
	 * @param videoInputPath
	 * @throws Exception
	 */
	public void convertorFirst(String videoInputPath,String tempPath) throws Exception {
		//不能固定文件名 如果已经有了这个名字的文件会运行不下去  所以在下面合并视频完成后删除无声视频  根据业务也可删除原视频
		//ffmpeg -i iron-man.mp4 -vcodec copy -an iron.mp4 消除原视频声音
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);

		command.add("-i");
		command.add(videoInputPath);

		command.add("-vcodec");

		command.add("copy");

		command.add("-an");
		//command.add("F:\\videos_upload_file\\200811G0KT1YRYFW\\video\\temp.mp4");
		command.add(tempPath);
/*		for (String c : command) {
			System.out.print(c + " ");
		}*/

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
	
	public void convertor(String tempPath, String mp3InputPath,
			double seconds, String videoOutputPath) throws Exception {
		//ffmpeg -i iron-man.mp4 -vcodec copy -an iron.mp4 消除原视频声音
		//ffmpeg -i mp4 -i mp3 -t 10 -y result.mp4  合并
//		ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);

		command.add("-i");
		command.add(tempPath);

		command.add("-i");
		command.add(mp3InputPath);
		
		command.add("-t");
		command.add(String.valueOf(seconds));
		
		command.add("-y");
		command.add(videoOutputPath);
		
//		for (String c : command) {
//			System.out.print(c + " ");
//		}
		
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
		//删除无声临时文件
		File file=new File(tempPath);
		file.delete();
	}

	public static void main(String[] args) {
	/*	MergeVideoMp3 ffmpeg = new MergeVideoMp3("E:\\ffmpeg-4.3.1-win64-static\\bin\\ffmpeg.exe");
		try {
			ffmpeg.convertorFirst("F:\\videos_upload_file\\200811G0KT1YRYFW\\video\\1.mp4");
			ffmpeg.convertor("F:\\videos_upload_file\\200811G0KT1YRYFW\\video\\1.mp4", "F:\\videos_upload_file\\1001\\video\\1.mp3", 14, "F:\\videos_upload_file\\200811G0KT1YRYFW\\video\\new.mp4");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

}
