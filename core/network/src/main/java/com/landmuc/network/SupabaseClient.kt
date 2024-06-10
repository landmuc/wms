package com.landmuc.network

import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

object SupabaseClient {
    val supabaseClient = createSupabaseClient(
        supabaseUrl = "https://vauuwmktobiucfbyrzqo.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZhdXV3bWt0b2JpdWNmYnlyenFvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTY0OTIyNDQsImV4cCI6MjAzMjA2ODI0NH0.LyeyzN8KgDHRHsFSV5r4eQkbUCQRcwNPrUYfqTzyL8s"
    ) {
        install(Postgrest)
        install(Auth)
        install(Realtime)
        install(ComposeAuth)
    }
}