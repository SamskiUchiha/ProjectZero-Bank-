package com.ex.screen;

import com.ex.AbstractApp;

import java.io.IOException;

public interface Screen {
    Screen makeScreen(AbstractApp app) throws IOException;
}
