# plugin-template

1. Create a new repository from this template.
2. Rename the example package and the `ExamplePlugin` class.
3. Update the plugin properties in `gradle.properties`.
    - Set `plugin.main` to the full package and class name of the renamed `ExamplePlugin` class.

## Updating Minecraft

When changing the target Minecraft version, update both:

* `minecraft.version` in `gradle.properties`
* `paper` in `gradle/libs.versions.toml`

For example:

```properties
# gradle.properties
minecraft.version=26.2
```

```toml
# gradle/libs.versions.toml
[versions]
paper = "26.2.build.65-beta"
```

Keep these versions compatible with each other.
