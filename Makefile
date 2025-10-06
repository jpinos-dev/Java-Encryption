SRC_DIR := src
BUILD_DIR := build
MAIN := FileEncryption

SOURCES := $(wildcard $(SRC_DIR)/*.java)

all:
	@mkdir -p $(BUILD_DIR)
	javac -d $(BUILD_DIR) $(SOURCES)

run: all
	java -cp $(BUILD_DIR) $(MAIN)

clean:
	rm -rf $(BUILD_DIR)

.PHONY: all run clean
