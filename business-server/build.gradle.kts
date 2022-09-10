tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":common-module"))
    implementation("io.github.openfeign:feign-gson:11.9.1")

}