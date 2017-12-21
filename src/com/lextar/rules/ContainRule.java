package com.lextar.rules;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ContainRule implements IScanRule {

	
	@Override
	public Boolean IsMatch(String currFile, List<String> scanFileList)  {
		
		String clzName = currFile.substring(currFile.lastIndexOf("\\") + 1, currFile.lastIndexOf(".vb"));
		String newClzStr = "New " + clzName;
		
		for(String fi : scanFileList){
			if(currFile.equals(fi))
				continue;
			
			Path path = Paths.get(fi);
			boolean result = false;
			try {
				result = Files.lines(path, StandardCharsets.ISO_8859_1)
						.filter(line -> !line.startsWith("'"))
						.anyMatch( line -> line.contains(clzName) && !line.contains(clzName + "_"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
//			list = stream
//					.filter(line -> !line.startsWith("line3"))
//					.map(String::toUpperCase)
//					.collect(Collectors.toList());

			if(result)
				return true;
		}
		
		
		return false;
	}

}
