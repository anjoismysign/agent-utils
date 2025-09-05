package io.github.anjoismysign.agentutils;

import dev.langchain4j.agentic.AgenticServices;
import io.github.anjoismysign.agentutils.task.GenerateSourceTreeRecursive;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public enum AgentUtils {
    INSTANCE;

    /**
     * Generates a source tree representation of a directory using java.nio.file.Path.
     *
     * @param root the directory to represent
     * @return a string representation of the source tree
     */
    @NotNull
    public String generateSourceTreeRecursive(@NotNull Path root){
        return new GenerateSourceTreeRecursive().run(root);
    }

}
