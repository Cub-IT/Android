package ua.university.navigation.flow

import ua.university.group.ui.add.screen.AddGroupScreenNavs
import ua.university.group.ui.edit.screen.EditGroupScreenNavs
import ua.university.group.ui.join.screen.JoinGroupScreenNavs
import ua.university.group.ui.list.screen.ListScreenNavs
import ua.university.group.ui.selected.screen.SelectedScreenNavs
import ua.university.navigation.navigator.Navigator
import ua.university.navigation.screen.Group
import ua.university.navigation.screen.NavTarget
import ua.university.navigation.screen.Post
import ua.university.navigation.screen.Settings
import ua.university.post.ui.add.screen.AddPostScreenNavs
import ua.university.post.ui.edit.screen.EditPostScreenNavs
import ua.university.settings.profile.ui.screen.ProfileScreenNavs

internal class GroupFlow(
    private val navigator: Navigator,
    private val onExit: () -> Unit,
) : NavigationFlow {
    override fun start() {
        navigator.navigateTo(navTarget = Group.List, navigationFlow = this)
    }

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
    override fun <T> getNavDirection(screenNavsClass: Class<T>): T? {
        return when (screenNavsClass.name) {
            ListScreenNavs::class.java.name -> onListScreen()
            SelectedScreenNavs::class.java.name -> onSelectedScreen()
            ProfileScreenNavs::class.java.name -> onProfileScreen()
            EditGroupScreenNavs::class.java.name -> onEditGroupScreen()
            AddGroupScreenNavs::class.java.name -> onAddGroupScreen()
            JoinGroupScreenNavs::class.java.name -> onJoinGroupScreen()
            AddPostScreenNavs::class.java.name -> onAddPostScreen()
            EditPostScreenNavs::class.java.name -> onEditPostScreen()

            else -> null
        } as? T
    }

    private fun onListScreen(): ListScreenNavs {
        return ListScreenNavs(
            onGroupClicked = { groupId ->
                navigator.navigateTo(navTarget = Group.Selected(groupId = groupId), navigationFlow = this)
            },
            onUserAvatarClicked = { navigator.navigateTo(navTarget = Settings.Profile, navigationFlow = this) },
            onAddGroupClicked = { navigator.navigateTo(navTarget = Group.Add, navigationFlow = this) },
            onJoinGroupClicked = { navigator.navigateTo(navTarget = Group.Join, navigationFlow = this) },
        )
    }

    private fun onSelectedScreen(): SelectedScreenNavs {
        return SelectedScreenNavs(
            onBackClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
            onUserAvatarClicked = { navigator.navigateTo(navTarget = Settings.Profile, navigationFlow = this) },
            onAddPostClicked = { groupId ->
                navigator.navigateTo(navTarget = Post.Add(groupId = groupId), navigationFlow = this)
            },
            onEditPostClicked = { groupId, postId ->
                navigator.navigateTo(navTarget = Post.Edit(groupId = groupId, postId = postId), navigationFlow = this)
            },
            onEditGroupClicked = { groupId ->
                navigator.navigateTo(navTarget = Group.Edit(groupId), navigationFlow = this)
            },
            onDeleteGroupClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) }
        )
    }

    private fun onProfileScreen(): ProfileScreenNavs {
        return ProfileScreenNavs(
            onBackClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
            onLogoutClicked = onExit,
        )
    }

    private fun onAddGroupScreen(): AddGroupScreenNavs {
        return AddGroupScreenNavs(
            onBackClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
            onCreateGroupClicked = { groupId ->
                navigator.navigateTo(navTarget = Group.Selected(groupId = groupId), navigationFlow = this)
            },
        )
    }

    private fun onEditGroupScreen(): EditGroupScreenNavs {
        return EditGroupScreenNavs(
            onBackClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
            onCreateGroupClicked = { navigator.navigateTo(NavTarget.Back, navigationFlow = this) },
        )
    }

    private fun onJoinGroupScreen(): JoinGroupScreenNavs {
        return JoinGroupScreenNavs(
            onBackClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
            onJoinGroupClicked = { groupId ->
                navigator.navigateTo(navTarget = Group.Selected(groupId = groupId), navigationFlow = this)
            },
        )
    }

    private fun onAddPostScreen(): AddPostScreenNavs {
        return AddPostScreenNavs(
            onBackClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
            onCreatePostClicked = { postId ->
                navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this)
            },
        )
    }

    private fun onEditPostScreen(): EditPostScreenNavs {
        return EditPostScreenNavs(
            onBackClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
            onCreatePostClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
        )
    }
}