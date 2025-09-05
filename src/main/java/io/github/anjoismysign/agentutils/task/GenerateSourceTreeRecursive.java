package io.github.anjoismysign.agentutils.task;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class GenerateSourceTreeRecursive {

    @NotNull
    public String run(@NotNull Path root){
        return run(root, 0);
    }

    /**
     * Generates a source tree representation of a directory using java.nio.file.Path.
     *
     * @param rootPath the directory to represent
     * @param depth    the current recursion depth
     * @return a string representation of the source tree
     */
    @NotNull
    private String run(Path rootPath, int depth) {
        StringBuilder result = new StringBuilder();
        String indent = "  ".repeat(Math.max(0, depth));

        if (rootPath == null) {
            System.out.println("rootPath == null");
            return "";
        }

        if (!Files.exists(rootPath)) {
            System.out.println("!Files.exists(rootPath)");
            return "";
        }

        String name = rootPath.getFileName() != null ? rootPath.getFileName().toString() : rootPath.toString();
        result.append(indent).append(name).append("/\n");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(rootPath)) {
            for (Path child : stream) {
                if (child.getFileName().toString().equals(".DS_Store")){
                    continue;
                }
                if (Files.isDirectory(child)) {
                    result.append(run(child, depth + 1));
                } else {
                    result.append(indent).append("  ").append(child.getFileName().toString()).append("\n");
                }
            }
        } catch (IOException exception) {
            System.err.println("Failed to list directory: " + rootPath);
        }

        return result.toString();
    }

}
