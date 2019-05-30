package edu.kis.powp.jobs2d.command.formatter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kis.powp.jobs2d.command.manager.SingleCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonFormatter implements Formatter{

    private final static List<Character> implementedFormat = Arrays.asList('[', '{');

    @Override
    public boolean validate(char firstChar) {
        return implementedFormat.contains(firstChar);
    }

    @Override
    public List<SingleCommand> createCommand(String inputTest) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(inputTest, new TypeReference<List<SingleCommand>>() {

            });
        } catch (IOException e) {
            return null;
        }
    }
}
