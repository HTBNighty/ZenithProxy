package com.zenith.discord.command.brigadier;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;

import java.util.function.Function;

public class CaseInsensitiveLiteralArgumentBuilder<S> extends LiteralArgumentBuilder<S> {
    private Function<CommandContext, Void> errorHandler;

    protected CaseInsensitiveLiteralArgumentBuilder(String literal) {
        super(literal);
    }

    public static <S> CaseInsensitiveLiteralArgumentBuilder<S> literal(final String name) {
        return new CaseInsensitiveLiteralArgumentBuilder<>(name);
    }

    public static <S> CaseInsensitiveLiteralArgumentBuilder<S> literal(final String name, Function<CommandContext, Void> errorHandler) {
        return new CaseInsensitiveLiteralArgumentBuilder<>(name);
    }

    public CaseInsensitiveLiteralArgumentBuilder<S> withErrorHandler(Function<CommandContext, Void> errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

    @Override
    public LiteralCommandNode<S> build() {
        final LiteralCommandNode<S> result = new CaseInsensitiveLiteralCommandNode<>(getLiteral(), getCommand(), getRequirement(), getRedirect(), getRedirectModifier(), isFork(), errorHandler);

        for (final CommandNode<S> argument : getArguments()) {
            result.addChild(argument);
        }

        return result;
    }
}
