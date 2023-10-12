package com.nhnacademy.node.in;

import com.nhnacademy.exception.AlreadyExistsException;
import com.nhnacademy.exception.InvalidArgumentException;
import com.nhnacademy.exception.OutOfBoundsException;
import com.nhnacademy.message.Message;
import com.nhnacademy.node.ActiveNode;
import com.nhnacademy.wire.Wire;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class InputNode extends ActiveNode{
    Wire[] outputWires;

    InputNode(int count) {
        super();

        if (count <= 0) {
            throw new InvalidArgumentException();
        }

        outputWires = new Wire[count];
    }

    public void connectOutputWire(int index, Wire wire) {
        if (outputWires.length <= index) {
            throw new OutOfBoundsException();
        }

        if (outputWires[index] != null) {
            throw new AlreadyExistsException();
        }

        outputWires[index] = wire;
    }

    public Wire[] getOutputWires() {
        return outputWires;
    }

    public Wire getoutputWire(int index) {
        if (index < 0 || outputWires.length <= index) {
            throw new OutOfBoundsException();
        }

        return outputWires[index];
    }


    void output(Message message) {
        log.trace("Message Out");
        for (Wire wire : outputWires) {
            if (wire != null) {
                wire.put(message);
            }
        }
    }

}
