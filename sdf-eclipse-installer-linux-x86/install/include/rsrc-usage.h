/* $Id: rsrc-usage.h 23509 2007-09-06 12:29:20Z economop $ */

#ifndef __RSRC_USAGE_H__
#define __RSRC_USAGE_H__

double STATS_Timer(void);
long STATS_Allocated(void);
void STATS_PageFlt(long *maj, long *min);
long STATS_ResidentSetSize(void);

#endif /* __RSRC_USAGE_H__ */
