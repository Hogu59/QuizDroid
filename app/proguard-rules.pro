# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# proguard
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

-keep class com.google.gson.** { *; }
-keepnames class * { @com.google.gson.annotations.SerializedName <fields>; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
-keepnames class kotlinx.serialization.json.** { *; }
-keepnames class kotlinx.serialization.internal.** { *; }
-keepnames class kotlinx.serialization.modules.** { *; }
-keepnames class * {
    @kotlinx.serialization.Serializable <fields>;
}
-keep class com.ottfstudio.quizdroid.data.model.** { *; }
