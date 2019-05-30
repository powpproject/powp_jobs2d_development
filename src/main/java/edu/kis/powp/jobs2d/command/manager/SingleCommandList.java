package edu.kis.powp.jobs2d.command.manager;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class SingleCommandList {

    @JacksonXmlProperty(localName = "command")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<SingleCommand> singleCommand;

    public SingleCommandList(List singleCommand) {
        this.singleCommand = singleCommand;
    }

    public SingleCommandList() {
    }

    public List getSingleCommand() {
        return singleCommand;
    }

    public void setSingleCommand(List singleCommand) {
        this.singleCommand = singleCommand;
    }
}
