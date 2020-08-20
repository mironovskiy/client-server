package by.epamtc.task06.server.entity;

import java.io.Serializable;
import java.util.Objects;

public class CodeBlock implements Component, Serializable {
    private String codeBlock;

    public CodeBlock() {}

    public CodeBlock(String codeblock) {
        this.codeBlock = codeblock;
    }

    public String getCodeBlock() {
        return codeBlock;
    }

    public void setCodeBlock(String codeBlock) {
        this.codeBlock = codeBlock;
    }

    public void addCode(String code){
        codeBlock += "\n" + code;
    }

    @Override
    public void print() {
        System.out.println(codeBlock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodeBlock)) return false;
        CodeBlock codeBlock1 = (CodeBlock) o;
        return Objects.equals(getCodeBlock(), codeBlock1.getCodeBlock());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodeBlock());
    }

    @Override
    public String toString() {
        return "CodeBlock{" +
                "codeBlock='" + codeBlock + '\'' +
                '}';
    }
}
