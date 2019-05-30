package edu.kis.powp.jobs2d.command.formatter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import edu.kis.powp.jobs2d.command.manager.SingleCommand;
import edu.kis.powp.jobs2d.command.manager.SingleCommandList;

import java.io.IOException;
import java.util.List;

public class XmlFormatter implements Formatter {

    private final static Character implementedFormat = '<';

    @Override
    public boolean validate(char firstChar) {
        return firstChar == implementedFormat;
    }

    @Override
    public List<SingleCommand> createCommand(String inputTest) {
        XmlMapper xmlMapper = new XmlMapper();
        SingleCommandList singleCommandList;
        try {
            singleCommandList = xmlMapper.readValue(inputTest, SingleCommandList.class);
        } catch (IOException e) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(singleCommandList.getSingleCommand(), new TypeReference<List<SingleCommand>>(){});
    }
}
