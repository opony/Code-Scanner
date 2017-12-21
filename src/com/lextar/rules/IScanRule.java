package com.lextar.rules;

import java.util.List;

public interface IScanRule {
	Boolean IsMatch(String currFile, List<String> scanFileList);
	
}
