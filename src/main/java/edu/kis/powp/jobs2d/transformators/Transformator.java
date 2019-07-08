package edu.kis.powp.jobs2d.transformators;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public interface Transformator {

    void transform(SetPositionCommand command);
    void transform(OperateToCommand command);
}
