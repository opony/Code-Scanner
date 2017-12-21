package com.lextar;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.lextar.rules.ContainRule;
import com.lextar.rules.IScanRule;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class Scan {

	public static void main(String[] args) throws IOException {
		
		Path dirPath = Paths.get("D:/SourceCode/MESWin3");
		
		
//		DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.{vb}");
//		for (Path path : stream) {
//		    System.out.println( path.getFileName() );
//		}
//		stream.close();
		
		
//		Files.walk(Paths.get("D:/SourceCode/MESWin3"))
//	     .filter(p -> p.toString().endsWith(".vb")).forEach(f -> {
//	    	 String filePath = f.toString();
//	    	 String clzName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf(".vb"));
//	    	 System.out.println(f.getFileName());
//	    	 
//	     });
		
		List<String> fileList = new ArrayList<>();
		//D:/SourceCode/MESWin3/winWIP
		Files.walk(Paths.get("D:/SourceCode/MESWin3"))
		.filter(p -> {
			String filePath = p.toString();
			
			
			
			if(filePath.endsWith("Reference.vb") || filePath.endsWith("Settings.vb")
					|| filePath.endsWith("AssemblyInfo.vb") || filePath.endsWith("Designer.vb")|| filePath.endsWith("designer.vb"))
				return false;
			else if(filePath.endsWith(".vb"))
				return true;
			
			return false;
		})
		.forEach( p -> fileList.add(p.toString()));
		
		System.out.println("File total count : " + fileList.size());
		
		IScanRule rule = new ContainRule();
		List<String> remvFileList = new ArrayList<>();
		fileList.forEach( fi -> {
			
			if(!rule.IsMatch(fi, fileList)){
				remvFileList.add(fi);
			}
			
		});
		
		System.out.println("remove file count : " + remvFileList.size());
		
		Path outFolder = Paths.get("D:/MesReduce");
		
		if(!Files.exists(outFolder))
			Files.createDirectories(outFolder);
		else
			FileUtils.cleanDirectory(outFolder.toFile());
		
		
		Files.write(Paths.get("D:/MesReduce/RemoveFile.txt"), remvFileList);
	}

}
