tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
dependencies {
    implementation(project(":common-module"))
}