package com.klezovich.robot.domain.command;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.klezovich.robot.domain.ScriptLine;
import com.klezovich.robot.domain.command.parser.CommandParser;
import com.klezovich.robot.domain.command.parser.CommandParser.ScriptLineProcessor;

public class CommandParserStringProcessorTest {

	
	@Test
	public void testLineSplitting(){
		
		CommandParser.ScriptLineProcessor slp = new ScriptLineProcessor();
		
		List<ScriptLine> li = slp.splitText("a\nb\nc");
		assertEquals(3, li.size() );
		
		li = slp.splitText("a");
		assertEquals(1, li.size() );
		
		li = slp.splitText("\n");
		assertEquals(0, li.size() );
		
		
		li = slp.splitText("\n\n");
		assertEquals(0, li.size() );
	}
	
	@Test
	public void testCommentRemoval(){
		ScriptLine resSl = null;
		CommandParser.ScriptLineProcessor slp = new ScriptLineProcessor();
		
		// No comments in line 
		resSl = slp.removeComments( new ScriptLine("",0) );
		assertEquals("",resSl.getText() );
		
		resSl = slp.removeComments( new ScriptLine("abc",0) );
		assertEquals("abc",resSl.getText() );
		
		resSl = slp.removeComments( new ScriptLine("abcdefg",0) );
		assertEquals("abcdefg",resSl.getText() );
		
		// Comments present in line 
		resSl = slp.removeComments( new ScriptLine("//",0) );
		assertEquals("", resSl.getText());
		
		ScriptLine sl = new ScriptLine("abc//water",0);
		resSl = slp.removeComments(sl);
		assertEquals("abc",resSl.getText() );
		
		resSl = slp.removeComments( new ScriptLine("//abc//",0) );
		assertEquals("",resSl.getText());
		
		resSl = slp.removeComments( new ScriptLine("1234//abc//",0) );
		assertEquals("1234",resSl.getText());
		
	}
}
