package com.nhnacademy.node.inout;

import lombok.extern.log4j.Log4j2;
import com.nhnacademy.exception.OutOfBoundsException;
import com.nhnacademy.message.Message;
import com.nhnacademy.node.ActiveNode;
import com.nhnacademy.wire.Wire;

@Log4j2
public class InOutNode extends ActiveNode {
    Wire[] inputWires;
    Wire[] outputWires;

    InOutNode(String name, int inCount, int outCount) {
        super(name);

        inputWires = new Wire[inCount];
        outputWires = new Wire[outCount];
    }

    protected InOutNode(int inCount, int outCount) {
        super();
        inputWires = new Wire[inCount];
        outputWires = new Wire[outCount];
    }

    public void connectOutputWire(int index, Wire wire) {
        if (index < 0 || outputWires.length <= index) {
            throw new OutOfBoundsException();
        }

        outputWires[index] = wire;
    }

    public int getOutputWireCount() {
        return outputWires.length;
    }

    public Wire getOutputWire(int index) {
        if (index < 0 || outputWires.length <= index) {
            throw new OutOfBoundsException();
        }

        return outputWires[index];
    }

    public void connectInputWire(int index, Wire wire) {
        if (index < 0 || inputWires.length <= index) {
            throw new OutOfBoundsException();
        }

        inputWires[index] = wire;
    }

    public int getInputWireCount() {
        return inputWires.length;
    }

    public Wire getInputWire(int index) {
        if (index < 0 || inputWires.length <= index) {
            throw new OutOfBoundsException();
        }

        return inputWires[index];
    }

    protected void output(Message message) {
        log.trace("Message Out");

        for (Wire port : outputWires) {
            if (port != null) {
                port.put(message);
            }
        }
    }
}
