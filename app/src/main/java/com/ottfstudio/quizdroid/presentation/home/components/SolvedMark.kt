package com.ottfstudio.quizdroid.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ottfstudio.quizdroid.R
import com.ottfstudio.quizdroid.ui.theme.Green100
import com.ottfstudio.quizdroid.ui.theme.Red500

@Composable
fun SolvedMark(
    isSolved: Boolean,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (isSolved) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = stringResource(R.string.completed),
                tint = Green100,
                modifier = Modifier.size(14.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.completed),
                fontSize = 14.sp,
                color = Green100,
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(14.dp)
                    .background(
                        color = Red500,
                        shape = CircleShape,
                    ),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_x_white),
                    contentDescription = stringResource(R.string.uncompleted),
                    modifier = Modifier.size(7.dp),
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.uncompleted),
                fontSize = 14.sp,
                color = Red500,
            )
        }
    }
}
